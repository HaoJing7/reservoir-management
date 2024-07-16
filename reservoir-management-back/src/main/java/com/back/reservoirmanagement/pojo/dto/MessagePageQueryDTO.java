package com.back.reservoirmanagement.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("消息查询参数")
public class MessagePageQueryDTO {
    @ApiModelProperty("页码")
    private Integer page;
    @ApiModelProperty("每页条数")
    private Integer pageSize;
    @ApiModelProperty("消息标题")
    private String title;
}
