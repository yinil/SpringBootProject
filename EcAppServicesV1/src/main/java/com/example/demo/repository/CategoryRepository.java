package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	List<Category> findByCategoryStore(String storeid);
	
	List<Category> findAll();
	
	List<Category> findByCategoryIdIn(List<Integer>ids);
	
}
