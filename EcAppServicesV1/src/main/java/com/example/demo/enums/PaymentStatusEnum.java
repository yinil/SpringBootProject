package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum PaymentStatusEnum {
	
	UNPAID(0, "The order has not been paid yet"),
	PAID(1, "The order has been paid"),
	REFUNDREQUESTED(2, "Refund has been requested for this order"),
	REFUNDED(3, "The order has been refunded");
	
	private Integer code;
	private String messaege;
	
	private PaymentStatusEnum(Integer code, String messaege) {
		this.messaege = messaege;
		this.code = code;
	}
	
	
	
}
