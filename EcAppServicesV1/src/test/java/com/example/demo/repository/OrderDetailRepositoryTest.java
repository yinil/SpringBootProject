package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.OrderDetail;

@SpringBootTest
@RunWith(SpringRunner.class)
class OrderDetailRepositoryTest {
	@Autowired
	private OrderDetailRepository repo;
	@Test
	void test() {
		List<OrderDetail> list = repo.findByDetailOrder("0212");
		assertEquals(2, list.size());
//		OrderDetail orderDetail = new OrderDetail();
//		orderDetail.setDetailId("11111");
//		orderDetail.setDetailItem("123");
//		orderDetail.setDetailOrder("0212");
//		orderDetail.setDetailPrice(BigDecimal.valueOf(38.8));
//		orderDetail.setDetailQuantity(1);
//		OrderDetail orderDetail2 = new OrderDetail();
//		orderDetail2.setDetailId("11110");
//		orderDetail2.setDetailItem("198");
//		orderDetail2.setDetailOrder("0212");
//		orderDetail2.setDetailPrice(BigDecimal.valueOf(3.5));
//		orderDetail2.setDetailQuantity(1);
//		List<OrderDetail> list = new ArrayList<>();
//		list.add(orderDetail);
//		list.add(orderDetail2);
//		List<OrderDetail> newlist = repo.saveAll(list);
//		assertEquals(2, newlist.size());
		
	}

}
