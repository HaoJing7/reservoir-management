package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.entity.User;
import com.back.reservoirmanagement.mapper.UserMapper;
import com.back.reservoirmanagement.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:tan hao
 * Date: 2023-10-18 12:13
 * Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, Object> login(User user) {
        return new HashMap<>();
    }
}
