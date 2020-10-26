package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.DynamicUpdate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Category;

@SpringBootTest
@RunWith(SpringRunner.class)
class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository repo;
	
	@Test
	void test() {
		assertNotNull(repo);
		testSaveCategory("Meat", "123456");
//		testFindCategory();
//		testUpdateCategory(1);
//		testfindByCategoryStore("123456");
	}

//	private void testfindByCategoryStore(String string) {
//		List<Category> list = repo.findByCategoryStore("123456");
//		System.out.println(list);
//	}

	private void testUpdateCategory(int id) {
		Optional<Category> category = repo.findById(id);
		Category category2 = category.get();
		System.out.println(category2);
		category2.setCategoryName("Appetizer");
		repo.save(category2);
		System.out.println(repo.findAll());
		
	}

	private void testFindCategory() {
		List<Category> categories = repo.findAll();
		System.out.println(categories);
	}

	private void testSaveCategory(String name, String storeid) {
		Category category = new Category();
		category.setCategoryName(name);
		category.setCategoryStore(storeid);
		repo.save(category);
	}

}
