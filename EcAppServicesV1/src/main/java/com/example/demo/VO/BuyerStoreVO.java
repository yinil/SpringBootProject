package com.example.demo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BuyerStoreVO {
	
	@JsonProperty("id")
	private String storeId;
	@JsonProperty("name")
	private String storeName;
}
