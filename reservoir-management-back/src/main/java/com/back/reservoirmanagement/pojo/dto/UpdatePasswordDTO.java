package com.back.reservoirmanagement.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:tan hao
 * Date: 2024-01-20 21:30
 * Description:
 */
@ApiModel(description = "用户登录传入的数据模型")
@Data
public class UpdatePasswordDTO {
    @ApiModelProperty("旧密码")
    private String oldPassword;

    @ApiModelProperty("新密码")
    private String newPassword;

    @ApiModelProperty("重复密码")
    private String confirmPassword;
}
