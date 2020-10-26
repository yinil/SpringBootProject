package com.example.demo.service;

import com.example.demo.DTO.OrderDTO;

public interface BuyerService {
	// look up one order
	OrderDTO findOrderOne(String clientid, String orderid);
	// cancel one order
	OrderDTO cancelOrderOne(String clientid, String orderid);
}
