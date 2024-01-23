package com.back.reservoirmanagement.service.impl;

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
                    .build();
            messageMapper.insert(message);
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        userMapper.deleteById(id);
    }
}
