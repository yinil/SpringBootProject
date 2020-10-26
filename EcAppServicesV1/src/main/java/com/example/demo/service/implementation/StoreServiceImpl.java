package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository repo;
		

	@Override
	public List<Store> findAll() {
		return repo.findAll();
	}


	@Override
	public Store findByStoreId(String storeid) {
		return repo.findByStoreId(storeid);
	}


	@Override
	public List<Store> findByStoreClient(String clientid) {
		return repo.findByStoreClient(clientid);
	}

	
}
