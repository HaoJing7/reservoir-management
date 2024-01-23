package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.pojo.dto.EmployeePageQueryDTO;
import com.back.reservoirmanagement.pojo.dto.SendMessageDTO;
import com.back.reservoirmanagement.pojo.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

public interface EmployeeService extends IService<User> {
    Page<User> page(EmployeePageQueryDTO employeePageQueryDTO);

    void sendMessage(SendMessageDTO sendMessageDTO);

    void deleteEmployee(Long id);

    void saveUser(User user);
}
