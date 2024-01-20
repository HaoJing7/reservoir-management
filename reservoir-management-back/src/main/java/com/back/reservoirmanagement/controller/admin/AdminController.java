package com.back.reservoirmanagement.controller.admin;

import com.back.reservoirmanagement.common.constant.JwtClaimsConstant;
import com.back.reservoirmanagement.common.properties.JwtProperties;
import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.common.utils.JwtUtil;
import com.back.reservoirmanagement.pojo.dto.UserLoginDTO;
import com.back.reservoirmanagement.pojo.entity.Admin;
import com.back.reservoirmanagement.service.AdminService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping("/admin/user")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 管理员登陆
     */
    @PostMapping("/login")
    public Result<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        Admin admin = adminService.login(userLoginDTO);

        //登录成功后，生成jwt令牌（把用户id放进token中）
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, admin.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        log.info("登陆成功！用户id为：{}", admin.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        return Result.success(data);
    }

    /**
     * 获得管理员信息
     */
    @GetMapping("/info")
    public Result<?> getInfo() {
        return Result.success(adminService.getInfoById());
    }

    /**
     * 退出登陆
     */
    @PostMapping("/logout")
    public Result<?> logout() {
        return Result.success();
    }

    /**
     * 修改密码
     * @param password
     * @return
     */
    @PutMapping("/password")
    public Result<?> updatePassword(String password) {
        adminService.updatePassword(password);
        return Result.success();
    }

    // TODO 添加员工
    /**
     * 添加管理员
     */
    @PostMapping
    public Result<?> add() {

        return Result.success();
    }
}
