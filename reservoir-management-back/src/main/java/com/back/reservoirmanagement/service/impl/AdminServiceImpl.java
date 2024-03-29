package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.common.constant.MessageConstant;
import com.back.reservoirmanagement.common.constant.StatusConstant;
import com.back.reservoirmanagement.common.context.BaseContext;
import com.back.reservoirmanagement.common.exception.LoginFailedException;
import com.back.reservoirmanagement.common.exception.PasswordFailedException;
import com.back.reservoirmanagement.common.utils.JwtUtil;
import com.back.reservoirmanagement.pojo.dto.UpdatePasswordDTO;
import com.back.reservoirmanagement.pojo.dto.UserLoginDTO;
import com.back.reservoirmanagement.pojo.entity.Admin;
import com.back.reservoirmanagement.mapper.AdminMapper;
import com.back.reservoirmanagement.pojo.entity.User;
import com.back.reservoirmanagement.pojo.vo.AdminInfoVO;
import com.back.reservoirmanagement.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:tan hao
 * Date: 2023-10-22 23:35
 * Description:
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    /**
     * 用户登陆
     * @param userLoginDTO
     * @return 登陆的用户
     */
    @Override
    public Admin login(UserLoginDTO userLoginDTO) {
        // 输入的账号和密码
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        // 根据用户名在数据库中查询用户数据
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUsername, username);
        Admin admin = adminMapper.selectOne(wrapper);

        // 处理各种异常
        // 用户不存在
        if (admin == null) {
            throw new LoginFailedException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 密码错误
        // 密码需要进行md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(admin.getPassword())) {
            throw new LoginFailedException(MessageConstant.PASSWORD_ERROR);
        }

        // 能够登陆
        return admin;

    }

    /**
     * 获得用户详细信息
     */
    @Override
    public AdminInfoVO getInfoById() {
        // 根据id在数据库中查询用户数据
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getId, BaseContext.getCurrentId());
        Admin admin = adminMapper.selectOne(wrapper);
        AdminInfoVO adminInfoVO = AdminInfoVO.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .build();
        return adminInfoVO;
    }

    /**
     * 修改密码
     * @param password
     */
    @Override
    public void updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        String oldPassword = updatePasswordDTO.getOldPassword();
        String password = updatePasswordDTO.getNewPassword();
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        Long currentId = BaseContext.getCurrentId();
        oldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        queryWrapper.eq(Admin::getId, currentId).eq(Admin::getPassword, oldPassword);
        Admin oldAdmin = adminMapper.selectOne(queryWrapper);
        // 如果没有查询到该admin则表示所输入的旧密码错误
        if (oldAdmin == null) {
            throw new PasswordFailedException(MessageConstant.OLD_PASSWORD_ERROR);
            // 抛出异常之后就不会执行下面的逻辑
        }

        // 进行md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 进行密码修改
        Admin admin = Admin.builder().password(password).build();

        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getId, currentId);
        adminMapper.update(admin, wrapper);
    }
}
