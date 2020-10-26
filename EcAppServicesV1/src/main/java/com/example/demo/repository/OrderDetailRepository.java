package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{
//	Page<OrderDetail> findbyDetailOrder(String orderid, Pageable pageable);
//	List<OrderDetail> findbyDetailOrder(String orderid);
	List<OrderDetail> findByDetailOrder(String detailOrder);
	
}	
