package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.PageRanges;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.VO.ResultVO;
import com.example.demo.converter.OrderFormToOrderDTO;
import com.example.demo.enums.ResultEnum;
import com.example.demo.exception.SellException;
import com.example.demo.form.OrderForm;
import com.example.demo.service.BuyerService;
import com.example.demo.service.OrderService;
import com.example.demo.utils.ResultVOUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class BuyerOrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private BuyerService buyerService;

//	create order
	@PostMapping("/create")
	public ResultVO<Map<String, String>> create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult) {
		log.info("[create order] reached" + orderForm.toString());
		if (bindingResult.hasErrors()) {
			log.error("[create order] Error in creating order");
			throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
					bindingResult.getFieldError().getDefaultMessage());
		}
		OrderDTO orderDTO = OrderFormToOrderDTO.convert(orderForm);
		if (CollectionUtils.isEmpty((orderDTO.getOrderDetailList()))) {
			log.error("[create order] The cart is empty");
			throw new SellException(ResultEnum.CART_EMPTY);
		}
		OrderDTO result = orderService.create(orderDTO);
		Map<String, String> map = new HashMap<>();
		map.put("orderId", result.getOrderId());
		return ResultVOUtil.success(map);
	}

//	order list
	@GetMapping("/list")
	public ResultVO<List<OrderDTO>> list(@RequestParam("clientid") String clientid,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		if (StringUtils.isEmpty(clientid)) {
			log.error("[list orders] client id is empty");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		PageRequest request = PageRequest.of(page, size);
		Page<OrderDTO> orderPage = orderService.findList(clientid, request);
		return ResultVOUtil.success(orderPage.getContent());
	}

//	order details
	@GetMapping("/detail")
	public ResultVO<OrderDTO> detail(@RequestParam("clientid") String clientid,
			@RequestParam("orderid") String orderid) {
		OrderDTO order = buyerService.findOrderOne(clientid, orderid);
		return ResultVOUtil.success(order);
	}

//	cancel order
	@PostMapping("/cancel")
	public ResultVO cancel(@RequestParam("clientid") String clientid, @RequestParam("orderid") String orderid) {
		OrderDTO orderDTO = buyerService.cancelOrderOne(clientid, orderid);
		return ResultVOUtil.success(orderDTO);
	}

//	get number of orders
	@GetMapping("/numberOfOrders")
	public ResultVO<Integer> numberOfOrders(@RequestParam("clientid") String clientid) {
		if (StringUtils.isEmpty(clientid)) {
			log.error("[list orders] client id is empty");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		int num = orderService.countOrdersByBuyer(clientid);
		return ResultVOUtil.success(num);
	}
	// put all the business logics in Service and let Controller use Service
}
