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

    /**
     * 登陆功能，返回token，token和用户id唯一对应
     * @Param 只有账号和密码的Admin对象
     * @Return
     **/
    @PostMapping("/login")
    public Result<?> login(@RequestBody Admin admin) {
        Admin admin1 = adminService.login(admin);
        if (admin1 != null) {
            // 根据用户id去获取token
            Map<String, Object> data = tokenService.getToken(admin1.getId());
            return Result.success(data, "登陆成功！");
        }
        return Result.fail("登陆失败！");
    }

    /**
     * 获取用户信息，此时前端还不知道用户id，因此需要使用token来获取id
     * @Param
     * @Return
     **/
    @GetMapping("/info")
    public Result<?> getInfo(@RequestHeader("X-Token") String token) {
        Integer userId = tokenService.getUserIdByToken(token);
        Admin admin = adminService.getById(userId);
        if (admin != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("userInfo", admin);
            return Result.success(data, "获取用户信息成功！");
        }
        return Result.fail("获取用户信息失败！");
    }

    /**
     * 注销，此时要删除token
     * @Param
     * @Return
     **/
    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token) {
        tokenService.deleteToken(token);
        return Result.success("成功退出！");
    }

    /**
     * 注册管理员用户，此时admin对象是没有id的，自增主键
     * @Param
     * @Return
     **/
    @PostMapping
    public Result<?> addAdminUser(@RequestBody Admin admin) {
        adminService.saveOrUpdate(admin);
        return Result.success("注册成功！");
    }

    /**
     * 根据用户id删除用户，然后前端再调用注销就会把token删除，不用在这里删
     * @Param
     * @Return
     **/
    //这个请求是/admin/user/1，把请求参数拼接在路径中 RESTful风格
    // 而不是/admin/user?id=1(id作为param)
    @DeleteMapping ("/{id}")
    public Result<?> deleteAdminUser(@PathVariable Integer id) {
        adminService.removeById(id);
        return Result.success("成功删除账号！");
    }

    /**
     * 修改用户信息，用户id在表中是hidden的
     * @Param
     * @Return
     **/
    @PutMapping
    public Result<?> updateAdminUser(@RequestBody Admin admin) {
        boolean update = adminService.updateById(admin);
        if (update) {
            return Result.success("修改成功！");
        }
        return Result.fail("修改失败");
    }

}
