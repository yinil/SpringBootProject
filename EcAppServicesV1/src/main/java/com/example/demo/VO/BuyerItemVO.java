package com.example.demo.VO;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class BuyerItemVO {
	
	private String itemId;
	
    private String itemName;
    
    private String itemDescription;
    
    private Integer itemCategory;
    
    private BigDecimal itemPrice;
    
    private String itemPic;
}
