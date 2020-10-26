package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Client {
	/*
	 * client_id VARCHAR(32) not null,
    client_type tinyint not null, -- 0 for buyer, 1 for seller
    client_email VARCHAR(320) not null,
    client_phone VARCHAR(16) not null,
    client_usename VARCHAR(32) not null,
    client_created_at TIMESTAMP not null default CURRENT_TIMESTAMP,
    client_updated_at TIMESTAMP not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,/
    */
	@Id
	private String clientId;
	
	private Integer clientType;
	
	private String clientEmail;
	
	private String clientPhone;
	
	private String clientUsername;
	
	private Date clientCreatedAt;
	
	private Date clientUpdatedAt;
}
