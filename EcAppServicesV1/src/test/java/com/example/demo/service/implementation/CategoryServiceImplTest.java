package com.example.demo.service.implementation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Category;
@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceImplTest {
	
	@Autowired
	private CategoryServiceImpl csi;


	@Test
	void test() {
		testSave();
//		testFindOne(1);
//		testFindAll();
//		testFindByStore("123456");
	}


	private void testSave() {
		Category category = new Category();
		category.setCategoryName("Dinner");
		category.setCategoryStore("0006");
		Category category2 = csi.save(category);
		System.out.println(category);
		System.out.println(category2);
	}


//	private void testFindByStore(String id) {
//		List<Category> category = csi.findCategoriesByCategoryStore(id);
//		assertNotNull(category);
//		System.out.println(category);
//	}


	private void testFindAll() {
		List<Category> categories = csi.findAll();
		assertNotNull(categories);
		System.out.println(categories);
	}


	private void testFindOne(int i) {
		Category category = csi.findOne(i);
		assertEquals(i, category.getCategoryId());
		System.out.println(category);
	}
	
	

}
