package com.back.reservoirmanagement.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("水库分类查找时传入的数据")
public class ReservoirQueryDTO {

    @ApiModelProperty("水库名称")
    private String name;

    @ApiModelProperty("页码")
    private int page;

    @ApiModelProperty("水库类型")
    private int type;
}
