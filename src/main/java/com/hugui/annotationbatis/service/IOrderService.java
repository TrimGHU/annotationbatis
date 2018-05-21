package com.hugui.annotationbatis.service;

import com.hugui.annotationbatis.entity.Order;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hugui
 * @since 2018-05-20
 */
public interface IOrderService extends IService<Order> {

	Order findById(String id);
	
}
