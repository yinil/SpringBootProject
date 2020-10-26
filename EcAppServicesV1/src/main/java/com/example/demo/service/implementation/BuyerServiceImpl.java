package com.example.demo.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.enums.ResultEnum;
import com.example.demo.exception.SellException;
import com.example.demo.service.BuyerService;
import com.example.demo.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService{

	@Autowired
	private OrderService os;
	
	@Override
	public OrderDTO findOrderOne(String clientid, String orderid) {
		return checkOrderOwner(clientid, orderid);
	}

	@Override
	public OrderDTO cancelOrderOne(String clientid, String orderid) {
		OrderDTO orderDTO = checkOrderOwner(clientid, orderid);
		if (orderDTO == null) {
			log.error("[cancel order] order does not exist");
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		return os.cancel(orderDTO);
	}
	
	private OrderDTO checkOrderOwner(String clientid, String orderid) {
		OrderDTO orderDTO = os.findOne(orderid);
		if (orderDTO == null) {
			return null;
		}
		// check if the order is the client's
		if (!orderDTO.getOrderBuyer().equalsIgnoreCase(clientid)) {
			log.error("[list orders] client id not matched, clientid = {}, orderBuyerid={}", clientid, orderDTO.getOrderBuyer());
			throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
		}
		return orderDTO;
	}

}
