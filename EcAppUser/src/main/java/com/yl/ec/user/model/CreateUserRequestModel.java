package com.yl.ec.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateUserRequestModel {
	@NotBlank(message = "Username cannot be empty.")
	@Size(min = 2, message = "Username cannot have less than 2 characters")
	private String username;
	
	@NotBlank(message = "Password cannot be empty.")
	@Size(min = 8, max = 16, message = "Password should have a length of at least 8 and not greater than 16")
	private String password;
	
	@NotBlank(message = "Email cannot be empty.")
	@Email(message = "Please check the format of email/")
	private String email;
}
