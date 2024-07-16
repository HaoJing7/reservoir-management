package com.back.reservoirmanagement.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("电站分类查找时传入的数据")
public class PowerStationQueryDTO {

    @ApiModelProperty("电站名称")
    private String powerStationName;

    @ApiModelProperty("电站类型")
    private String type;

    @ApiModelProperty("是否梯级")
    private String isStair;

//    @ApiModelProperty("页码")
//    private int page;
}
