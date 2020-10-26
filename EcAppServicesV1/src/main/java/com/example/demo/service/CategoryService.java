package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Category;

public interface CategoryService {
	Category findOne(Integer id);
	List<Category> findAll();
	List<Category> findByCategoryStore(String storeid);
	Category save(Category category);
	List<Category> findByCategoryIdIn(List<Integer>ids);
}
