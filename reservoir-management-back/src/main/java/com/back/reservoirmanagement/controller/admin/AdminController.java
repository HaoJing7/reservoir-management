package com.back.reservoirmanagement.controller.admin;

import com.back.reservoirmanagement.entity.Admin;
import com.back.reservoirmanagement.service.AdminService;
import com.back.reservoirmanagement.service.TokenService;
import com.back.reservoirmanagement.vo.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:tan hao
 * Date: 2023-10-22 22:58
 * Description:
 * 用于处理后台有关管理员的请求
 */
@RestController
@RequestMapping("/admin/user")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody Admin admin) {
        Admin admin1 = adminService.login(admin);
        if (admin1 != null) {
            // 获取token
            Map<String, Object> data = tokenService.getToken(admin1.getId());
            return Result.success(data, "登陆成功！");
        }
        return Result.fail("登陆失败！");
    }

    @GetMapping("/info")
    public Result<?> getInfo(String token) {
        Integer userId = tokenService.getUserIdByToken(token);
        Admin admin = adminService.getById(userId);
        if (admin != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("username", admin.getUsername());
            return Result.success(data);
        }
        return Result.fail("获取用户信息失败！");
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token) {
        tokenService.deleteToken(token);
        return Result.success();
    }
}
