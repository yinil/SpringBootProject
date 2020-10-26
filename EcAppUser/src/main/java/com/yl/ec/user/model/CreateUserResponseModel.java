package com.yl.ec.user.model;

import lombok.Data;

@Data
public class CreateUserResponseModel {
	private String username;
	private String email;
	private String userId;
}
