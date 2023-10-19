package com.back.reservoirmanagement.controller;

import com.back.reservoirmanagement.entity.User;
import com.back.reservoirmanagement.service.UserService;
import com.back.reservoirmanagement.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:tan hao
 * Date: 2023-10-18 12:05
 * Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        Map<String, Object> data = userService.login(user);
        return Result.success(data);
    }

    @GetMapping("/info")
    public Result<?> getUserInfo(String token) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "github");
        return Result.success(data);
    }

    @GetMapping("/logout")
    public Result<?> logout(String token) {
        System.out.println("撤回推送测试");
        return Result.success();
    }
}
