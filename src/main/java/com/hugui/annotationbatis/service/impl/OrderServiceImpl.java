package com.hugui.annotationbatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hugui.annotationbatis.annotation.NeedtoSelectColumn;
import com.hugui.annotationbatis.entity.Order;
import com.hugui.annotationbatis.mapper.OrderMapper;
import com.hugui.annotationbatis.mapper.UserMapper;
import com.hugui.annotationbatis.service.IOrderService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hugui
 * @since 2018-05-20
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Override
	@NeedtoSelectColumn(selectColumn = "username", fromClass = UserMapper.class, fromMethod = "selectById", findByColumn = "userId")
	public Order findById(String id) {
		Order order = orderMapper.findById(id);
		return order;
	}

}
