package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
	
	PRODUCT_NOT_EXIST(10, "The product doesn't exist"),
	PRODUCT_STOCK_ERROR(11, "Error in updating the stock of the product"),
	ORDER_NOT_EXIST(12, "The order doesn't exist"),
	ORDER_DETAIL_NOT_EXIST(13, "The order's details do not exist"),
	ORDER_STATUS_ERROR(14, "Incorrect order status"),
	ORDER_UPDATE_FAIL(15, "Failed to update order"),
	ORDER_DETAIL_EMPTY(16, "The order does not contain any details"),
	ORDER_PAY_STATUS_ERROR(17, "Incorrect order payment status"),
	PARAM_ERROR(1, "Error exists in parameters"),
	CART_EMPTY(18, "The cart is empty"),
	ORDER_OWNER_ERROR(19, "This order doesn't match with the client")
	;
	
	private Integer code;
	private String msg;
	
	ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
