package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.VO.BuyerItemVO;
import com.example.demo.VO.BuyerStoreDetailVO;
import com.example.demo.VO.BuyerStoreInfoVO;
import com.example.demo.VO.BuyerStoreVO;
import com.example.demo.VO.ResultVO;
import com.example.demo.entity.Item;
import com.example.demo.entity.Store;
import com.example.demo.enums.ItemStatusEnum;
import com.example.demo.service.ItemService;
import com.example.demo.service.StoreService;

@RestController
@RequestMapping("/stores")
@CrossOrigin(origins = "http://localhost:4200")
public class BuyerStoreController {
	@Autowired
	private StoreService ss;
	
	@Autowired
	private ItemService is;
	
	@GetMapping
	public ResultVO<List<BuyerStoreVO>> stores() {
		List<Store> stores = ss.findAll();
		ResultVO<List<BuyerStoreVO>> resultVO = new ResultVO<>();
		List<BuyerStoreVO> storelist = new ArrayList<>();
		for (Store store : stores) {
			BuyerStoreVO buyerStoreVO = new BuyerStoreVO();
			BeanUtils.copyProperties(store, buyerStoreVO);
			storelist.add(buyerStoreVO);
		}
		resultVO.setData(storelist);
		resultVO.setCode(0);
		resultVO.setMsg("success");
		return resultVO;
 	}
	// 1. look up all items for sell
	// 2. look for categories at once
	// 3. put together the data
	@GetMapping("/{id}")
	public ResultVO<BuyerStoreDetailVO> items(@PathVariable("id") String storeid) {
		Store store = ss.findByStoreId(storeid);
		BuyerStoreInfoVO bStoreInfoVO = new BuyerStoreInfoVO();
		BeanUtils.copyProperties(store, bStoreInfoVO);
		// find all items for sale in this store
		List<Item> items = is.findByItemStatusAndItemStore(ItemStatusEnum.ForSale.getCode(), storeid);
		List<BuyerItemVO> buyerItemVOs = new ArrayList<>();
		for (Item item:items) {
			BuyerItemVO bItemVO = new BuyerItemVO();
			BeanUtils.copyProperties(item, bItemVO);
			buyerItemVOs.add(bItemVO);
		}
		BuyerStoreDetailVO buyerStoreDetailVO = new BuyerStoreDetailVO();
		buyerStoreDetailVO.setItems(buyerItemVOs);
		buyerStoreDetailVO.setStore(bStoreInfoVO);
		ResultVO<BuyerStoreDetailVO> resultVO = new ResultVO<>();
		resultVO.setData(buyerStoreDetailVO);
		resultVO.setCode(0);
		resultVO.setMsg("success");
		return resultVO;
	}
}
