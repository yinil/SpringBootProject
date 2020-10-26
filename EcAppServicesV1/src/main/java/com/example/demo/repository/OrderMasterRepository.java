package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String>{
	
	Page<OrderMaster> findByOrderBuyer(String orderBuyer, Pageable pageable);
	
	OrderMaster findByOrderId(String orderid);
	
	int countByOrderBuyer(String orderBuyer);
	
}
