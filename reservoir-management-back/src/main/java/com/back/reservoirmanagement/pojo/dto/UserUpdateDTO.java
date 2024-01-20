package com.back.reservoirmanagement.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("小程序用户编辑基本信息传入的数据")
@Data
public class UserUpdateDTO implements Serializable {
    @ApiModelProperty("id，不可改")
    private long id;

    @ApiModelProperty("姓名")
    private String realname;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("家庭地址")
    private String homeAddress;

    @ApiModelProperty("工作地点")
    private String workPlace;

    @ApiModelProperty("个人头像")
    private String icon;
}
