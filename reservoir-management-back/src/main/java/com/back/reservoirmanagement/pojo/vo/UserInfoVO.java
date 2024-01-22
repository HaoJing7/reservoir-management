package com.back.reservoirmanagement.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "获取员工信息返回的数据格式")
public class UserInfoVO {
    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("姓名")
    private String realname;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("个人家庭地址")
    private String homeAddress;

    @ApiModelProperty("工作地点")
    private String workPlace;

    @ApiModelProperty("头像")
    private String icon;

    @ApiModelProperty("注册时间")
    private LocalDateTime createTime;
}
