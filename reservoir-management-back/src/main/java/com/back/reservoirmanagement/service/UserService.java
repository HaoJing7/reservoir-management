package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.pojo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    public User login(User user);
}
