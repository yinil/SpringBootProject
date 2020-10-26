package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import com.example.demo.enums.OrderStatusEnum;
import com.example.demo.enums.PaymentStatusEnum;

import lombok.Data;

@Data
@Entity
@DynamicUpdate
public class OrderMaster {
	
	@Id
	private String orderId;
	
	private BigDecimal orderAmount;
	
	private Integer orderPay = PaymentStatusEnum.UNPAID.getCode();
	
	private Integer orderStatus = OrderStatusEnum.PLACED.getCode();
	
	private String orderBuyer;
	
	private String orderFullname;
	
	private String orderZip;
	
	private String orderPhone;
	
	private String orderEmail;
	
	private String orderCity;
	
	private String orderState;
	
	private String orderStreet1;
	
	private String orderStreet2;
	
	@Column(insertable = false)
	private Date orderCreatedAt;
	
	@Column(insertable = false)
	private Date orderUpdatedAt;
}
