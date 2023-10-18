package com.back.mybackadmintemplate.service;

import com.back.mybackadmintemplate.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface UserService extends IService<User> {
    Map<String, Object> login(User user);
}
