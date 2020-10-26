package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum ItemStatusEnum {
	
	ForSale(1, "this item is up for sale"),
	NotForSale(0, "this item is not for sale"),
	NotFound(2, "this item is not found");
	// in stock, out of stock?
	
	private Integer code;
	private String msg;
	
	ItemStatusEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
