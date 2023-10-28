package com.back.reservoirmanagement.controller.app;

import com.back.reservoirmanagement.entity.User;
import com.back.reservoirmanagement.service.UserService;
import com.back.reservoirmanagement.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/app/user")
@RestController
@Api(tags = "小程序用户端接口")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 员工用户账号密码登录
     * @param user 封装的信息
     * @return 当前成功登录的员工的信息
     */
    @PostMapping("/login")
    @ApiOperation("小程序用户登录")
    public Result<User> login(@RequestBody User user){
        User userInfo = userService.login(user);
        if (userInfo==null){
            return Result.fail("登录失败,用户名或密码错误");
        }
        return Result.success(userInfo);
    }
}
