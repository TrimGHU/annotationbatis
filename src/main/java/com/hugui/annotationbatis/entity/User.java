package com.hugui.annotationbatis.entity;

import java.io.Serializable;
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
@TableName("user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@TableField("username")
	private String username;

	@TableField("password")
	private String password;

	@TableField("gender")
	private Integer gender;

	@TableField("age")
	private Integer age;

	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;

	@Override
	public String toString() {
		return "User{" + ", id=" + id + ", username=" + username + ", password=" + password + ", gender=" + gender
				+ ", age=" + age + ", createTime=" + createTime + ", updateTime=" + updateTime + "}";
	}
}
