package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@DynamicUpdate
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    
	private String categoryName;
    
	private String categoryStore;
	
	@Column(insertable = false)
	private Date categoryCreatedAt;
	
	@Column(insertable = false)
	private Date categoryUpdatedAt;
    
}
