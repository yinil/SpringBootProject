package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Item;


@SpringBootTest
@RunWith(SpringRunner.class)
class ItemRepositoryTest {
	
	@Autowired
	private ItemRepository repo;
	
	@Test
	void test() {
//		testFindByItemStore("123456");
//		testFindByItemStatusAndItemStore(1, "123456");
	}

//	void testSave() {
//		Item item = new Item("123", "Rosted Duck", 1, "123456", BigDecimal.valueOf(38.8), 500, 1);
//		Item it = repo.save(item);
//		assertNotNull(it);
//	}
	
//	private void testFindByItemStatusAndItemStore(Integer status, String storeid) {
//		List<Item> items = repo.findByItemStatusAndItemStore(status, storeid);
//		assertEquals(1, items.size());
//		assertEquals(storeid, items.get(0).getItemStore());
//		assertEquals(status, items.get(0).getItemStatus());
//		assertEquals("123", items.get(0).getItemId());
//	}
	
//	private void testFindByItemStore(String storeid) {
//		List<Item>items = repo.findByItemStore(storeid);
//		assertEquals(2, items.size());
//	}
//
//	void testFindByItemStatus(Integer status) {
//		List<Item> items = repo.findByItemStatus(status);
//		assertEquals(2, items.size());
//	}
	

}
