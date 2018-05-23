package com.hugui.annotationbatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author hugui
 * @since 2018-05-20
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("`order`")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	@TableField("user_id")
	private Long userId;
	@TableField("address")
	private String address;
	@TableField("product_name")
	private String productName;
	@TableField("price")
	private BigDecimal price;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;

	private String userName;

	@Override
	public String toString() {
		return "Order{" + ", id=" + id + ", userId=" + userId + ", address=" + address + ", productName=" + productName
				+ ", price=" + price + ", createTime=" + createTime + ", updateTime=" + updateTime + "}";
	}
}
