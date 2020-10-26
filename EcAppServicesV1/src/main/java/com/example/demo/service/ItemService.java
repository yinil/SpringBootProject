package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.DTO.CartDTO;
import com.example.demo.entity.Item;

public interface ItemService {
	Item findOne(String itemid);
	Item save(Item item);
	Page<Item> findAll(Pageable pageable);
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
	// add and subtract stock
	void increaseStock(List<CartDTO> cart);
	void decreaseStock(List<CartDTO> cart);
	BigDecimal findItemPriceByItemId(String itemid);
}
