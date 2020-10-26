package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
	PLACED(0, "Order has been placed"),
	CANCELED(1, "Order is canceled"),
	ONROUTE(2, "Order is being delivered"),
	DELIVERED(3, "Order has been delivered and thus closed");
	
	private Integer code;
	private String message;
	private OrderStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
