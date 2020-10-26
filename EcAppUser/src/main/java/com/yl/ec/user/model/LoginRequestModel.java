package com.yl.ec.user.model;

import lombok.Data;

@Data
public class LoginRequestModel {
	private String email;
	private String password;
}
