package com.hugui.annotationbatis.service.impl;

import com.hugui.annotationbatis.entity.Order;
import com.hugui.annotationbatis.mapper.OrderMapper;
import com.hugui.annotationbatis.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hugui
 * @since 2018-05-20
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

	@Autowired
	private OrderMapper mapper;
	
	@Override
	public Order findById(String id) {
		return mapper.findById(id);
	}

}
