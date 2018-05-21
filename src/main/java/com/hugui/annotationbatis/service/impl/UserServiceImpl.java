package com.hugui.annotationbatis.service.impl;

import com.hugui.annotationbatis.entity.User;
import com.hugui.annotationbatis.mapper.UserMapper;
import com.hugui.annotationbatis.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
