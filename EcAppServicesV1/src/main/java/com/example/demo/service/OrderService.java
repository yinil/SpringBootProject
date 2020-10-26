package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.DTO.OrderDTO;

public interface OrderService {
	OrderDTO create(OrderDTO orderDTO);
	
	OrderDTO findOne(String orderId);
	
	Page<OrderDTO> findList(String clientid, Pageable pageable);
	
	OrderDTO cancel(OrderDTO orderDTO);
	
	OrderDTO finish(OrderDTO orderDTO);
	
	OrderDTO paid(OrderDTO orderDTO);
	
	int countOrdersByBuyer(String orderBuyer);
}
