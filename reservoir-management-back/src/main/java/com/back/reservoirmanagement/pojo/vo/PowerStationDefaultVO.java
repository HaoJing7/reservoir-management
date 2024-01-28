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
@ApiModel(description = "电站默认调用返回的数据格式")
public class PowerStationDefaultVO implements Serializable {

    @ApiModelProperty("电站id")
    private Long powerStationId;

    @ApiModelProperty("电站编号")
    private String powerStationNumber;

    @ApiModelProperty("电站名称")
    private String powerStationName;

    @ApiModelProperty("电站地址")
    private String address;

    @ApiModelProperty("电站类型")
    private int type;       //电站类型(1:蓄水式 2:引水式 3: 径流式)

    @ApiModelProperty("对应水库id")
    private Long reservoirId;

    @ApiModelProperty("机组数量")
    private int unit;

    @ApiModelProperty("装机容量")
    private String contain;

    @ApiModelProperty("年均径流量")
    private BigDecimal annualAverageRunoff;

    @ApiModelProperty("年均发电量")
    private BigDecimal annualAveragePowerGeneration;

    @ApiModelProperty("最大引用流量")
    private BigDecimal maxReferenceTaffic;

    @ApiModelProperty("最小引用流量")
    private BigDecimal minReferenceTaffic;
}
