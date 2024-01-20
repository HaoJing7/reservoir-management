package com.back.reservoirmanagement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("水库操作时传入的数据")
public class ReservoirDTO implements Serializable {

    @ApiModelProperty("水库编号")
    private String number;

    @ApiModelProperty("水库名称")
    private String name;

    @ApiModelProperty("水库类型")
    private String address;

    @ApiModelProperty("设计库容")
    private String designCapacity;

    @ApiModelProperty("最大库容")
    private String maxCapacity;

    @ApiModelProperty("死库容")
    private String deadCapacity;

    @ApiModelProperty("正常水位")
    private String normalWaterLevel;

    @ApiModelProperty("最高水位")
    private String maxWaterLevel;

    @ApiModelProperty("死水位")
    private String deadWaterLevel;
}
