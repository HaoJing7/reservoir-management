package com.back.mybackadmintemplate.service.impl;

import com.back.mybackadmintemplate.entity.User;
import com.back.mybackadmintemplate.mapper.UserMapper;
import com.back.mybackadmintemplate.service.UserService;
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
