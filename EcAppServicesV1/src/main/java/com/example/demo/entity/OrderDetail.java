package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@DynamicUpdate
public class OrderDetail {
	
	@Id
	private String detailId;
	
	private String detailItem;
	
	private Integer detailQuantity;
	
	private BigDecimal detailPrice;
	
	private String detailOrder;
	
	private String detailItemname;
	
	private String detailItempic;
	
	@Column(insertable = false)
	private Date detailCreatedAt;
	
	@Column(insertable = false)
	private Date detailUpdatedAt;
}
