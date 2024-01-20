package com.back.reservoirmanagement.controller.app;


import com.back.reservoirmanagement.common.properties.JwtProperties;
import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.common.utils.JwtUtil;
import com.back.reservoirmanagement.pojo.dto.FindBackPasswordDTO;
import com.back.reservoirmanagement.pojo.dto.UserLoginDTO;
import com.back.reservoirmanagement.pojo.dto.UserUpdateDTO;
import com.back.reservoirmanagement.pojo.entity.User;
import com.back.reservoirmanagement.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

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

    //TODO 更新基本信息，更改密码，密码找回

    /**
     * 编辑基本信息
     * @param userUpdateDTO
     * @return
     */
    @PutMapping
    @ApiOperation("编辑用户基本信息")
    public Result update(@RequestBody UserUpdateDTO userUpdateDTO){
        log.info("新的信息：{}", userUpdateDTO);

        User userInfo=userService.getById(userUpdateDTO.getId());

        //理论上点开编辑效果是默认填上原先的基本信息的,不需要进行非空判断，为了方便接口文档测试，这里加了判断
        if(StringUtils.isNotEmpty(userUpdateDTO.getRealname()))
            userInfo.setRealname(userUpdateDTO.getRealname());
        if(StringUtils.isNotEmpty(userUpdateDTO.getGender()))
            userInfo.setGender(userUpdateDTO.getGender());
        if(StringUtils.isNotEmpty(userUpdateDTO.getPhone()))
            userInfo.setPhone(userUpdateDTO.getPhone());
        if(StringUtils.isNotEmpty(userUpdateDTO.getHomeAddress()))
            userInfo.setHomeAddress(userUpdateDTO.getHomeAddress());
        if(StringUtils.isNotEmpty(userUpdateDTO.getWorkPlace()))
            userInfo.setWorkPlace(userUpdateDTO.getWorkPlace());

        userService.updateById(userInfo);
        return Result.success();
    }

    @PostMapping("/updatePassword")
    @ApiOperation("更改密码")
    public Result updatePassword(User user){
        User userInfo =userService.getById(user.getId());

        String password =user.getPassword();
        password=DigestUtils.md5DigestAsHex(password.getBytes());//加密

        userInfo.setPassword(password);
        userService.updateById(userInfo);
        return Result.success();
    }

    @PostMapping("/findBackPassword")
    @ApiOperation("找回密码")
    public Result findBackPassword(FindBackPasswordDTO findBackPasswordDTO){
        log.info("findBackPasswordDTP：{}",findBackPasswordDTO);

        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,findBackPasswordDTO.getUsername());
        User userInfo =userService.getOne(queryWrapper);

        if(userInfo==null)
            return Result.error("该用户不存在");

        String password =findBackPasswordDTO.getPassword();
        password=DigestUtils.md5DigestAsHex(password.getBytes());//加密

        userInfo.setPassword(password);
        userService.updateById(userInfo);
        return Result.success();
    }

}
