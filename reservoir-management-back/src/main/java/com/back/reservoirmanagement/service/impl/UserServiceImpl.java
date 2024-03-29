package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.common.context.BaseContext;
import com.back.reservoirmanagement.pojo.entity.User;
import com.back.reservoirmanagement.mapper.UserMapper;
import com.back.reservoirmanagement.pojo.vo.UserInfoVO;
import com.back.reservoirmanagement.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    /**
     * 员工用户账号密码登录
     * @param user 封装的信息
     * @return 当前成功登录的员工的信息
     */
    @Override
    public User login(User user) {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        wrapper.eq(User::getPassword,user.getPassword());
        User userInfo = this.getOne(wrapper);
        return userInfo;
    }


    /**
     * 获取用户信息
     * @return
     */
    @Override
    public UserInfoVO getInfoById() {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, BaseContext.getCurrentId());
        User userInfo = this.getOne(wrapper);
        UserInfoVO userVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo,userVO);
        return userVO;
    }
}
