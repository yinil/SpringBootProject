package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.VO.ResultVO;
import com.example.demo.entity.Item;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ItemService;

@RestController
@RequestMapping("/items")
public class BuyerProductController {
	
	@Autowired
	private ItemService is;
	
	@Autowired
	private CategoryService cs;
	
	@GetMapping("/list")
	public ResultVO list() {
		
		
		ResultVO resultVO = new ResultVO();
		
		
		
		
		return resultVO;
	}
	
	@GetMapping("/{id}")
	public void item(@PathVariable("id") String itemid) {
		Item item = is.findOne(itemid);
		String storeid = item.getItemStore();
		// redirect to store page
	}
}
