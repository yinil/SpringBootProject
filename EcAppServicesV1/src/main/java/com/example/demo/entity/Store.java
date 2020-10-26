package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Store {
	@Id
	private String storeId;
	
	private String storeName;
	
	private String storePhone;
	
	private String storeEmail;
	
	private String storeWebsite;
	
	private String storeZip;
	
	private String storeCity;
	
	private String storeState;
	
	private String storeStreet1;
	
	private String storeStreet2;
	
	private String storeClient;
	
	private Date storeCreatedAt;
	
	private Date storeUpdatedAt;
	
}
