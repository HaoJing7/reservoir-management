package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;


public interface UserService extends IService<User> {
    Map<String, Object> login(User user);
}
