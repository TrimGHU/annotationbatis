package com.hugui.annotationbatis.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hugui.annotationbatis.entity.Order;
import com.hugui.annotationbatis.service.IOrderService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hugui
 * @since 2018-05-20
 */
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@PostMapping(value = "/add")
	public String add(HttpServletRequest request, @RequestBody Map<String, String> map) {
		Order order = new Order();
		order.setUserId(1L);
		order.setPrice(new BigDecimal(11.22));
		order.setProductName("cake");
		order.setAddress("guangdong shenzhen xixiang");

		orderService.insert(order);
		return "order is created , order id is " + order.getId();
	}

	@GetMapping("/{id}")
	public Order query(@PathVariable(name = "id") String id) {
		return orderService.findById(id);
	}
}
