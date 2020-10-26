package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.OrderMaster;

@SpringBootTest
@RunWith(SpringRunner.class)
class OrderMasterRepositoryTest {

	@Autowired
	private OrderMasterRepository repo;
	
	@Test
	void test() {
		
		PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("orderAmount"));
		Page<OrderMaster> result = repo.findByOrderBuyer("9288", pageRequest);
//		assertNotEquals(0, result.getTotalElements());
		assertEquals(2, result.getTotalElements());
		
		
//		OrderMaster orderMaster = new OrderMaster();
//		orderMaster.setOrderAmount(BigDecimal.valueOf(39.9));
//		orderMaster.setOrderBuyer("9288");
//		orderMaster.setOrderCity("Manhattan");
//		orderMaster.setOrderEmail("take@away.com");
//		orderMaster.setOrderFullname("the name");
//		orderMaster.setOrderId("0213");
//		orderMaster.setOrderPhone("1938395039");
//		orderMaster.setOrderState("NY");
//		orderMaster.setOrderStore("123456");
//		orderMaster.setOrderStreet1("some street");
//		orderMaster.setOrderStreet2("some street 2");
//		orderMaster.setOrderZip("20103");
//		
//		OrderMaster neworder = repo.save(orderMaster);
//		assertNotNull(neworder);
	}

}
