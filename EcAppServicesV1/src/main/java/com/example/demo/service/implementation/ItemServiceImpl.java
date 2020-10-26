package com.example.demo.service.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.CartDTO;
import com.example.demo.entity.Item;
import com.example.demo.enums.ItemStatusEnum;
import com.example.demo.enums.ResultEnum;
import com.example.demo.exception.SellException;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository repo;

	@Override
	public Item findOne(String itemid) {
		return repo.findById(itemid).orElseGet(null);
	}


	@Override
	public Item save(Item item) {
		return repo.save(item);
	}

	@Override
	public Page<Item> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public List<Item> findByItemStatus(Integer itemStatus) {
		return repo.findByItemStatus(itemStatus);
	}

	@Override
	public List<Item> findByItemStore(String storeid) {
		return repo.findByItemStore(storeid);
	}

	@Override
	public List<Item> findByItemStatusAndItemStore(Integer status, String storeid) {
		return repo.findByItemStatusAndItemStore(status, storeid);
	}

	@Override
	public List<Item> findByItemCategory(Integer itemCategory) {
		return repo.findByItemCategory(itemCategory);
	}


	@Override
	@Transactional
	public void increaseStock(List<CartDTO> cart) {
		for (CartDTO cartDTO : cart) {
			Item item = repo.findById(cartDTO.getItemId()).orElse(null);
			if (item == null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			item.setItemStock(item.getItemStock() + cartDTO.getItemQuantity());
			repo.save(item);
		}
	}


	@Override
	@Transactional
	public void decreaseStock(List<CartDTO> cart) {
		for (CartDTO cartDTO : cart) {
			Item item = repo.findById(cartDTO.getItemId()).orElse(null);
			if (item == null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			Integer result = item.getItemStock() - cartDTO.getItemQuantity();
			if (result < 0) {
				throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
			}
			item.setItemStock(result);
			repo.save(item);
		}
	}


	@Override
	public BigDecimal findItemPriceByItemId(String itemid) {
		Optional<Item> it = repo.findById(itemid);
		Item item = it.orElseGet(null);
		if (item == null) {
			throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		}
		return it.get().getItemPrice();
		
	}

}
