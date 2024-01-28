package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Author:tan hao
 * Date: 2024-01-28 12:36
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("app_powerstation")
public class PowerStation {
    @TableId(type = IdType.AUTO)
    private Long powerStationId;
    // 电站编号
    private String powerStationNumber;
    // 电站名称
    private String powerStationName;
    // 电站类型
    private Integer type;
    // 是否阶梯
    private Boolean IsStair;
    // 对应水库id
    private Long reservoirId;
    // 机组数量
    private Integer unit;
    // 装机容量
    private String contain;
    // 年均径流量
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal annualAverageRunoff;
    // 年均发电量
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal annualAveragePowerGeneration;
    // 电站地址
    private String address;
    // 最大引用流量
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal maxReferenceTaffic;
    // 最小引用流量
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal minReferenceTaffic;
}
