package com.yl.ec.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yl.ec.user.data.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
}
