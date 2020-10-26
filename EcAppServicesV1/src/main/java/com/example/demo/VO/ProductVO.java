package com.example.demo.VO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
//商品（包含类目）
@Data
public class ProductVO {
	@JsonProperty("name")
	private String itemName;
	
	@JsonProperty("type")
	private Integer itemCategory;
	
	@JsonProperty("products")
	private List<ItemVO> itemVOList;
	
}
