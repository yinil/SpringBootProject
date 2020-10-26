package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entity.Store;

@CrossOrigin("http://localhost:4200")
public interface StoreRepository extends JpaRepository<Store, String> {
	List<Store> findByStoreClient(String storeClient);
	Store findByStoreId(String storeId); 
	List<Store> findByStoreIdIn(List<String> storeidlist);
	List<Store> findAll();
	
}
