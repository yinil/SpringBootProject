package com.example.demo.service.implementation;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.OrderDetail;
import com.example.demo.enums.OrderStatusEnum;
import com.example.demo.enums.PaymentStatusEnum;

@SpringBootTest
@RunWith(SpringRunner.class)
class OrderServiceImplTest {
	@Autowired
	private OrderServiceImpl osi;
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImplTest.class);

	@Test
	void test() {
		// order paid
		OrderDTO orderDTO = osi.findOne("1592269731109415897");
		OrderDTO result = osi.paid(orderDTO);
		assertEquals(PaymentStatusEnum.PAID.getCode(), result.getOrderPay());
		// close order
//		OrderDTO orderDTO = osi.findOne("1592269731109415897");
//		OrderDTO result = osi.finish(orderDTO);
//		assertEquals(OrderStatusEnum.DELIVERED.getCode(), result.getOrderStatus());
		// cancel order
//		OrderDTO orderDTO = osi.findOne("1592269731109415897");
//		OrderDTO result = osi.cancel(orderDTO);
		// find list by id
//		PageRequest request = PageRequest.of(0, 2);
//		Page<OrderDTO> orderdtoPage = osi.findList("0112", request);
//		log.info("{}", orderdtoPage);
		// find one
//		OrderDTO orderDTO = osi.findOne("1592269731109415897");
//		log.info("find one: {}", orderDTO);
		// create
//		OrderDTO orderDTO = new OrderDTO();
//		orderDTO.setOrderAmount(BigDecimal.valueOf(0));
//		orderDTO.setOrderBuyer("0112");
//		orderDTO.setOrderCity("LA");
//		orderDTO.setOrderEmail("email");
//		orderDTO.setOrderFullname("full name");
//		orderDTO.setOrderPhone("9385923859");
//		orderDTO.setOrderState("CA");
//		orderDTO.setOrderStore("123456");
//		orderDTO.setOrderStreet1("street1");
//		orderDTO.setOrderStreet2("street2");
//		orderDTO.setOrderZip("19384");
//		// cart
//		List<OrderDetail> cart = new ArrayList<>();
//		OrderDetail o1 = new OrderDetail();
//		o1.setDetailItem("123");
//		o1.setDetailQuantity(2);
//		OrderDetail o2 = new OrderDetail();
//		o2.setDetailItem("177");
//		o2.setDetailQuantity(3);
//		cart.add(o2);
//		cart.add(o1);
//		orderDTO.setOrderDetailList(cart);
//		OrderDTO result = osi.create(orderDTO);
	}

}
