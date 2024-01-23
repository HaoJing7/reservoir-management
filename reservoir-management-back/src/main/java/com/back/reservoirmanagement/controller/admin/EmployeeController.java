package com.back.reservoirmanagement.controller.admin;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.dto.EmployeePageQueryDTO;
import com.back.reservoirmanagement.pojo.dto.SendMessageDTO;
import com.back.reservoirmanagement.pojo.entity.User;
import com.back.reservoirmanagement.service.EmployeeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author:tan hao
 * Date: 2024-01-23 12:25
 * Description:
 */
@RestController
@Slf4j
@RequestMapping("/admin/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 分页查询员工列表
     */
    @GetMapping("/page")
    public Result<?> getEmployeeList(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("传入的请求参数为：{}", employeePageQueryDTO);
        Page<User> page = employeeService.page(employeePageQueryDTO);
        return Result.success(page);
    }

    /**
     * 群发消息
     */
    @PostMapping("/message")
    public Result<?> sendMessage(@RequestBody SendMessageDTO sendMessageDTO) {
        employeeService.sendMessage(sendMessageDTO);
        return Result.success();
    }

    /**
     * 删除员工
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return Result.success();
    }

    /**
     * 添加员工
     */
    @PostMapping("/add")
    public Result<?> addEmployee(@RequestBody User user) {
        employeeService.saveUser(user);
        return Result.success();
    }
}
