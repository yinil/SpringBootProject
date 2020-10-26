package com.example.demo.service.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.demo.DTO.CartDTO;
import com.example.demo.DTO.OrderDTO;
import com.example.demo.converter.OrderMasterToOrderDTO;
import com.example.demo.entity.Item;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.OrderMaster;
import com.example.demo.enums.OrderStatusEnum;
import com.example.demo.enums.PaymentStatusEnum;
import com.example.demo.enums.ResultEnum;
import com.example.demo.exception.SellException;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderMasterRepository;
import com.example.demo.service.ItemService;
import com.example.demo.service.OrderService;
import com.example.demo.utils.KeyUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private ItemService is;
	
	@Autowired
	private OrderDetailRepository odrepo;
	
	@Autowired
	private OrderMasterRepository omrepo;
	
	@Override
	@Transactional
	public OrderDTO create(OrderDTO orderDTO) {
		BigDecimal amount = new BigDecimal(0);
		String orderId = KeyUtil.genUniqueKey();
		// 1. lookup price and entity
		for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
			Item item = is.findOne(orderDetail.getDetailItem());
			if (item == null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			// 2. calculate total price
			amount = item.getItemPrice()
					.multiply(new BigDecimal(orderDetail.getDetailQuantity()))
					.add(amount);
			orderDetail.setDetailId(KeyUtil.genUniqueKey());
			orderDetail.setDetailOrder(orderId);
			orderDetail.setDetailPrice(item.getItemPrice());
			orderDetail.setDetailItemname(item.getItemName());
			orderDetail.setDetailItempic(item.getItemPic());
			odrepo.save(orderDetail);
		}
		// 3. write in 2 databases
		OrderMaster orderMaster = new OrderMaster();
		orderDTO.setOrderId(orderId);
		orderDTO.setOrderAmount(amount);
		orderDTO.setOrderPay(PaymentStatusEnum.UNPAID.getCode());
		orderDTO.setOrderStatus(OrderStatusEnum.PLACED.getCode());
		BeanUtils.copyProperties(orderDTO, orderMaster);
		omrepo.save(orderMaster);
		// 4. decrease item stock
		List<CartDTO> cart = orderDTO.getOrderDetailList().stream()
			.map(e -> new CartDTO(e.getDetailItem(), e.getDetailQuantity()))
			.collect(Collectors.toList());
		is.decreaseStock(cart);
		return orderDTO;
	}

	@Override
	public OrderDTO findOne(String orderId) {
		OrderMaster orderMaster = omrepo.findByOrderId(orderId);
		if (orderMaster == null) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		List<OrderDetail> orderDetails = odrepo.findByDetailOrder(orderId);
		if (CollectionUtils.isEmpty(orderDetails)) {
			throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
		}
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		orderDTO.setOrderDetailList(orderDetails);
		return orderDTO;
	}

	@Override
	public Page<OrderDTO> findList(String clientid, Pageable pageable) {
		Page<OrderMaster> masterPage = omrepo.findByOrderBuyer(clientid, pageable);
		List<OrderDTO> orderDTOs = OrderMasterToOrderDTO.convert(masterPage.getContent());
		Page<OrderDTO> dtoPage = new PageImpl<OrderDTO>(orderDTOs, pageable, masterPage.getTotalElements());
		return dtoPage;
	}

	@Override
	@Transactional
	public OrderDTO cancel(OrderDTO orderDTO) {
		OrderMaster orderMaster = new OrderMaster();
		
		// 判断订单状态
		if (! orderDTO.getOrderStatus().equals(OrderStatusEnum.PLACED.getCode())) {
			log.error("[cancel order] incorrect order status, orderId = {}, orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		// 修改订单状态
		orderDTO.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updated = omrepo.save(orderMaster);
		if (updated == null) {
			log.error("[cancel order] failed to cancel order");
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		// 返回库存
		if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
			log.error("[cancel order] the order does not contain any items, orderDTO = {}", orderDTO);
			throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
		}
		List<CartDTO> cart = orderDTO.getOrderDetailList().stream()
					.map(e -> new CartDTO(e.getDetailItem(), e.getDetailQuantity()))
					.collect(Collectors.toList());
		is.increaseStock(cart);
		// 如果已付款，退款
		if (orderDTO.getOrderPay() == PaymentStatusEnum.PAID.getCode()) {
			// TODO: refund
		}
		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO finish(OrderDTO orderDTO) {
		if (orderDTO.getOrderStatus() != OrderStatusEnum.PLACED.getCode()) {
			log.error("[close order] incorrect order status, orderid = {}, orderstatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		orderDTO.setOrderStatus(OrderStatusEnum.DELIVERED.getCode());
		OrderMaster orderMaster = new OrderMaster();		
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updated = omrepo.save(orderMaster);
		if (updated == null) {
			log.error("[close order] failed to update the order, orderMaster = {}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO paid(OrderDTO orderDTO) {
		if (!orderDTO.getOrderPay().equals(OrderStatusEnum.PLACED.getCode()) ) {
			log.error("[paid order] incorrect order status, orderid = {}, orderstatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		if (orderDTO.getOrderPay().equals(PaymentStatusEnum.PAID.getCode())) {
			log.error("[paid order] incorrect order payment status, orderDTO = {}", orderDTO);
			throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
		}
		orderDTO.setOrderPay(PaymentStatusEnum.PAID.getCode());
		OrderMaster orderMaster = new OrderMaster();		
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updated = omrepo.save(orderMaster);
		if (updated == null) {
			log.error("[paid order] failed to update the order, orderMaster = {}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		return orderDTO;
	}

	@Override
	public int countOrdersByBuyer(String orderBuyer) {
		return omrepo.countByOrderBuyer(orderBuyer);
	}

}
