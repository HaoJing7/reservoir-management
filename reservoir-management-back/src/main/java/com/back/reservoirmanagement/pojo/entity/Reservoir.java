package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("app_reservoir")
public class Reservoir {
    @ApiModelProperty("水库编号")
    private String number;
    @ApiModelProperty("水库名称")
    private String name;
    @ApiModelProperty("水库地址")
    private String address;
    @ApiModelProperty("有效容量")
    private String usableCapacity;
    @ApiModelProperty("设计容量")
    private String designCapacity;
    @ApiModelProperty("最大容量")
    private String maxCapacity;
    @ApiModelProperty("最小容量")
    private String minCapacity;
    @ApiModelProperty("死容量")
    private String deadCapacity;
    @ApiModelProperty("正常水位")
    private String normalWaterLevel;
    @ApiModelProperty("梅汛期水位")
    private String meixunWaterLevel;
    @ApiModelProperty("台风期水位")
    private String typhoonWaterLevel;
    @ApiModelProperty("洪水期水位")
    private String floodWaterLevel;
    @ApiModelProperty("最高水位")
    private String maxWaterLevel;
    @ApiModelProperty("死水位")
    private String deadWaterLevel;
}
