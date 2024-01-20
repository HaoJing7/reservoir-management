package com.back.reservoirmanagement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("水库分页时传入的数据")
public class ReservoirPageQueryDTO {

    @ApiModelProperty("水库名称")
    private String name;

    @ApiModelProperty("页码")
    private int page;

    @ApiModelProperty("每页显示记录数")
    private int pageSize;
}
