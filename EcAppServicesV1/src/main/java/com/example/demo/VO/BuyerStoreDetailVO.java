package com.example.demo.VO;

import java.util.List;

import lombok.Data;

@Data
public class BuyerStoreDetailVO {
	private BuyerStoreInfoVO store;
	private List<BuyerItemVO> items;
}
