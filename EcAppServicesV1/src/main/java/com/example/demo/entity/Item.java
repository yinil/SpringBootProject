package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.demo.enums.ItemStatusEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Item {
	
	@Id
    private String itemId;
	
    private String itemName;
    
    private String itemDescription;
    
    private Integer itemCategory;
    
    private String itemStore;
    
    private String itemStoreName;
    
    private BigDecimal itemPrice;
    
    private Integer itemStock; // 1 in stock, 0 out of stock
    
    private String itemPic; // default to a url
    
    private Integer itemStatus = ItemStatusEnum.ForSale.getCode();
    
    @Column(insertable = false)
    private Date itemCreatedAt;
    
    @Column(insertable = false)
    private Date itemUpdatedAt;

	public Item(String itemId, String itemName, Integer itemCategory, String itemStore, BigDecimal itemPrice,
			Integer itemStock, Integer itemStatus) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.itemStore = itemStore;
		this.itemPrice = itemPrice;
		this.itemStock = itemStock;
		this.itemStatus = itemStatus;
	}

    
    
}
