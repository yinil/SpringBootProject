package com.example.demo.service.implementation;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Item;


@SpringBootTest
@RunWith(SpringRunner.class)
class ItemServiceImplTest {
	
	@Autowired
	private ItemServiceImpl isi;

	@Test
	void test() {
//		Item item = isi.save(new Item("198", "Smashed potatoes", 6, "0006", BigDecimal.valueOf(3.5), 500, 1));
//		assertNotNull(item);
		PageRequest pageRequest = PageRequest.of(1, 20);
		Page<Item> items = isi.findAll(pageRequest);
		System.out.println(items.getSize());
		System.out.println(items);
		System.out.println(items.getTotalPages());
	}

}
