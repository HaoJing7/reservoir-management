package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.entity.Admin;
import com.back.reservoirmanagement.mapper.AdminMapper;
import com.back.reservoirmanagement.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author:tan hao
 * Date: 2023-10-22 23:35
 * Description:
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    /**
     * 登陆方法
     * @return 登陆的管理员admin
     */
    @Override
    public Admin login(Admin admin) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getPassword, admin.getPassword())
                .eq(Admin::getUserName, admin.getUserName());
        return adminMapper.selectOne(queryWrapper);
    }
}
