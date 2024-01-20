package com.back.reservoirmanagement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("找回密码传输的数据模型")
public class FindBackPasswordDTO implements Serializable {
    @ApiModelProperty("找回账户")
    private String username;
    @ApiModelProperty("新密码")
    private String password;
}
