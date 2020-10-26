package com.example.demo.VO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemVO {
	@JsonProperty("id")
	private String itemId;
	
	@JsonProperty("name")
	private String itemName;
	
	@JsonProperty("price")
	private BigDecimal itemPrice;
	
	@JsonProperty("description")
	private String itemDescription;
	
	@JsonProperty("icon")
	private String itemPic;
	
	@JsonProperty("storeid")
	private String itemStore;
	
}
