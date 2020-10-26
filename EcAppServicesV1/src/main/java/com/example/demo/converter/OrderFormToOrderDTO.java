package com.example.demo.converter;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.OrderDetail;
import com.example.demo.enums.ResultEnum;
import com.example.demo.exception.SellException;
import com.example.demo.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderFormToOrderDTO {
	public static OrderDTO convert(OrderForm orderForm) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderBuyer(orderForm.getId());
		orderDTO.setOrderCity(orderForm.getCity());
		orderDTO.setOrderEmail(orderForm.getEmail());
		orderDTO.setOrderFullname(orderForm.getName());
		orderDTO.setOrderPhone(orderForm.getPhone());
		orderDTO.setOrderStreet1(orderForm.getStreet1());
		orderDTO.setOrderStreet2(orderForm.getStreet2());
		orderDTO.setOrderZip(orderForm.getZip());
		orderDTO.setOrderState(orderForm.getState());
		List<OrderDetail> orderDetails = new ArrayList<>();
		orderDTO.setOrderDetailList(orderForm.getItems());
		return orderDTO;
	}
}
