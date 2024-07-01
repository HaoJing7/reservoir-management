package com.back.reservoirmanagement.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("消息的按条件查找封装的数据")
public class MessageQueryDTO {

    @ApiModelProperty("消息")
    private String title;

}
