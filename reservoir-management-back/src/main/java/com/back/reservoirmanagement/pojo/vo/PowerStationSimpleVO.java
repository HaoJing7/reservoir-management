package com.back.reservoirmanagement.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "电站简单数据")
public class PowerStationSimpleVO implements Serializable {

    @ApiModelProperty("电站id")
    private Long powerStationId;

    @ApiModelProperty("电站名称")
    private String powerStationName;

    @ApiModelProperty("对应水库id")
    private Long reservoirId;
}
