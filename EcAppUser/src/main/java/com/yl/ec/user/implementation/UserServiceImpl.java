package com.yl.ec.user.implementation;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yl.ec.user.data.UserEntity;
import com.yl.ec.user.dto.UserDto;
import com.yl.ec.user.repository.UserRepository;
import com.yl.ec.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository repo;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoderncoder;
	
	@Override
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoderncoder.encode(userDetails.getPassword()));
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDetails, userEntity);
		repo.save(userEntity);
		
		UserDto ret = new UserDto();
		BeanUtils.copyProperties(userEntity, ret);
		return ret;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = repo.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new User(user.getEmail(), user.getEncryptedPassword(), true, true, true, true, new ArrayList());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity user = repo.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		System.out.println(userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		UserEntity userEntity = repo.findByUserId(userId);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		System.out.println(userDto);
		return userDto;
	}
	
	

}
