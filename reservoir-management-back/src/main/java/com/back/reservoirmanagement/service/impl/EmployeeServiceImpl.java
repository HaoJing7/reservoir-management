package com.back.reservoirmanagement.service.impl;

import com.back.reservoirmanagement.common.exception.DuplicateUsernameException;
import com.back.reservoirmanagement.mapper.MessageMapper;
import com.back.reservoirmanagement.mapper.UserMapper;
import com.back.reservoirmanagement.pojo.dto.EmployeePageQueryDTO;
import com.back.reservoirmanagement.pojo.dto.SendMessageDTO;
import com.back.reservoirmanagement.pojo.entity.Message;
import com.back.reservoirmanagement.pojo.entity.User;
import com.back.reservoirmanagement.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

/**
 * Author:tan hao
 * Date: 2024-01-23 12:26
 * Description:
 */
@Service
@Slf4j
public class EmployeeServiceImpl extends ServiceImpl<UserMapper, User> implements EmployeeService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;
    @Override
    public Page<User> page(EmployeePageQueryDTO employeePageQueryDTO) {
        Page<User> page = new Page<>(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!employeePageQueryDTO.getName().isEmpty(), User::getRealname, employeePageQueryDTO.getName())
                .like(!employeePageQueryDTO.getPhone().isEmpty(), User::getPhone, employeePageQueryDTO.getPhone());
        userMapper.selectPage(page, queryWrapper);
        return page;
    }

    @Override
    public void sendMessage(SendMessageDTO sendMessageDTO) {
        for (Long id : sendMessageDTO.getEmployeeIds()) {
            Message message = Message.builder()
                    .employeeId(id)
                    .level(sendMessageDTO.getLevel())
                    .content(sendMessageDTO.getContent())
                    .checked(0)
                    .finished(0)
                    .createTime(LocalDateTime.now())
                    .build();
            messageMapper.insert(message);
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void saveUser(User user) {
        String password = user.getPassword();
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setCreateTime(LocalDateTime.now());
        user.setIcon("https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/reservoir-a1b9048b-e165-0934-49e0-25d97865fc78.png");
        // 查询是否有重复用户名
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User selectOne = userMapper.selectOne(queryWrapper);
        if (selectOne == null) {
            userMapper.insert(user);
        } else {
            throw new DuplicateUsernameException("用户名重复！");
        }
    }
}
