package com.example.demo.service.implementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository repo;

	@Override
	public Category findOne(Integer id) {
		return repo.findById(id).orElseGet(null);
	}

	@Override
	public List<Category> findAll() {
		return repo.findAll();
	}

	@Override
	public Category save(Category category) {
		return repo.save(category);
	}

	@Override
	public List<Category> findByCategoryIdIn(List<Integer> ids) {
		// TODO Auto-generated method stub
		return repo.findByCategoryIdIn(ids);
	}

	@Override
	public List<Category> findByCategoryStore(String storeid) {
		// TODO Auto-generated method stub
		return repo.findByCategoryStore(storeid);
	}

}
