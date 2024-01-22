package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.pojo.entity.User;
import com.back.reservoirmanagement.pojo.vo.UserInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    /**
     * 用户账号密码登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 获取当前员工用户的信息
     * @return
     */
    UserInfoVO getInfoById();
}
