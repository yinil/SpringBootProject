package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String> {
	
	// find in stock status
	List<Item> findByItemStatus(Integer itemStatus);
//	Page<Item> findByItemStatus(Integer status, Pageable pageable);
	
	// find by store
	List<Item> findByItemStore(String storeid);
//	Page<Item> findByItemStore(String storeid, Pageable pageable);
	
	// find by store and status
	List<Item> findByItemStatusAndItemStore(Integer status, String storeid);
//	Page<Item>findByItemStatusAndItemStore(Integer status, String storeid, Pageable pageable);
	
	// find by store and category
	List<Item> findByItemCategory(Integer itemCategory);
	
}
