package com.back.reservoirmanagement.controller.app;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.dto.UserLoginDTO;
import com.back.reservoirmanagement.pojo.entity.User;
import com.back.reservoirmanagement.properties.JwtProperties;
import com.back.reservoirmanagement.service.UserService;
import com.back.reservoirmanagement.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/app/user")
@RestController
@Api(tags = "小程序用户端接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 员工用户账号密码登录
     * @param userLoginDTO 封装的信息
     * @return 当前成功登录的员工的信息
     */
    @PostMapping("/login")
    @ApiOperation("小程序用户登录")
    public Result<User> login(@RequestBody UserLoginDTO userLoginDTO){

        //对输入的密码进行加密处理
        String password=userLoginDTO.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        userLoginDTO.setPassword(password);

        //将dto的内容拷贝给user
        User user= User.builder()
                .username(userLoginDTO.getUsername())
                .password(userLoginDTO.getPassword())
                .build();

        User userInfo = userService.login(user);
        if (userInfo==null){
            return Result.error("登录失败,用户名或密码错误");
        }

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userInfo.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        log.info("{}登录成功",userInfo.getUsername());
        log.info("token令牌；{}",token);

        return Result.success(userInfo);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping
    @ApiOperation("新增用户")
    public Result addUser(@RequestBody User user){

        //对输入的密码进行加密处理
        String password=user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        user.setPassword(password);
        user.setCreateTime(LocalDateTime.now());
        userService.save(user);
        return Result.success();

    }
}
