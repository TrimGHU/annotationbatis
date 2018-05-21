package com.hugui.annotationbatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hugui.annotationbatis.entity.Order;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hugui
 * @since 2018-05-20
 */

@CacheConfig(cacheNames = "order")
public interface OrderMapper extends BaseMapper<Order> {

	@Select("select user_id as userId, product_name as productName, price, address from `order` where id =#{id}")
	@Cacheable(key = "#p0")
	Order findById(@Param("id") String id);

}
