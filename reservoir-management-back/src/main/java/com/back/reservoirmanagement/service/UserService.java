package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.entity.User;
import com.back.reservoirmanagement.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    public User login(User user);
}
