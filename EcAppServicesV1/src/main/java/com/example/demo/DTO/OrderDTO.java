package com.example.demo.DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.entity.OrderDetail;
import com.example.demo.utils.serializer.DateToLong;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Data
public class OrderDTO {
	private String orderId;
	
	private BigDecimal orderAmount;
	
	private Integer orderPay;
	
	private Integer orderStatus;
	
	private String orderBuyer;
	
	private String orderFullname;
	
	private String orderZip;
	
	private String orderPhone;
	
	private String orderEmail;
	
	private String orderCity;
	
	private String orderState;
	
	private String orderStreet1;
	
	private String orderStreet2;
	
	@JsonSerialize(using = DateToLong.class)
	private Date orderCreatedAt;
	@JsonSerialize(using = DateToLong.class)
	private Date orderUpdatedAt;
	
	private List<OrderDetail> orderDetailList;
}
