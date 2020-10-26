package com.example.demo.form;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.example.demo.entity.OrderDetail;

import lombok.Data;

@Data
public class OrderForm {
	
	@NotBlank(message="Your name is required.")
	private String name;
	
	@NotBlank(message="Your zip code is required.")
	private String zip;
	
	@NotBlank(message="Your phone number is required.")
	private String phone;
	
	@NotBlank(message="Your email is required.")
	private String email;
	
	@NotBlank(message="Your city is required.")
	private String city;

	@NotBlank(message="Your state is required.")
	private String state;
	
	@NotBlank(message="Your street address is required.")
	private String street1;
	
	private String street2;
	
	@NotBlank(message="Your id is required.")
	private String id;
	
//	@NotBlank(message="Your cart is empty.")
	private List<OrderDetail> items;
	
}