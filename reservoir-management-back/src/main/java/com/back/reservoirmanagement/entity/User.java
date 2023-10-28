package com.back.reservoirmanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("app_user")
@ApiModel("用户类")
public class User {

    @TableId(type=IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户的真实姓名")
    private String realname;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("家庭地址")
    private String homeAddress;

    @ApiModelProperty("工作地点")
    private String workPlace;

    @ApiModelProperty("个人头像")
    private String icon;

    @ApiModelProperty("用户创建时间")
    private LocalDateTime createTime;
}
