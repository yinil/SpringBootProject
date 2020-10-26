package com.yl.ec.user.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserDto implements Serializable 
{
	private static final long serialVersionUID = 986701007382148508L;
	private String username;
	private String password;
	private String email;
	private String userId;
	private String encryptedPassword;
	private Timestamp current_time;
	private long id;
}
