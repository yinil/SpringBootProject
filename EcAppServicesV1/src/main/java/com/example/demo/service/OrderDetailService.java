package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.OrderDetail;

public interface OrderDetailService {
	List<OrderDetail> findbyDetailOrder(String orderid);
}
