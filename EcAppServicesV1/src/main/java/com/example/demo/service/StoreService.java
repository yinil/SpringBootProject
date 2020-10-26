package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Store;

public interface StoreService {
	Store findByStoreId(String storeid);
	List<Store> findAll();
	List<Store> findByStoreClient(String clientid);
}
