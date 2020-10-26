package com.example.demo.DTO;

import lombok.Data;

@Data
public class CartDTO {
	private String itemId;
	private Integer itemQuantity;
	public CartDTO(String itemId, Integer itemQuantity) {
		this.itemId = itemId;
		this.itemQuantity = itemQuantity;
	}
}
