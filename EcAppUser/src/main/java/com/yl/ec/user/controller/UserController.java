package com.yl.ec.user.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.client.http.HttpResponse;
import com.yl.ec.user.dto.UserDto;
import com.yl.ec.user.model.CreateUserRequestModel;
import com.yl.ec.user.model.CreateUserResponseModel;
import com.yl.ec.user.model.UserResponseModel;
import com.yl.ec.user.repository.UserRepository;
import com.yl.ec.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	UserService uss;
	
//	@GetMapping("/status/check")
//	public String status() {
//		System.out.println("hit");
//		String ret = "user-ec/user: working" + ", with token = " + env.getProperty("token.secret");
//		return ret;
//	}
	
	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		UserDto createdUser = uss.createUser(userDto);
		CreateUserResponseModel body = new CreateUserResponseModel();
		BeanUtils.copyProperties(createdUser, body);
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}
	
	@GetMapping(value = "/{userId}")
	public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {
		UserDto userDto = uss.getUserByUserId(userId);
		UserResponseModel userResponseModel = new UserResponseModel();
		BeanUtils.copyProperties(userDto, userResponseModel);
		return ResponseEntity.status(HttpStatus.OK).body(userResponseModel);
	}
}
