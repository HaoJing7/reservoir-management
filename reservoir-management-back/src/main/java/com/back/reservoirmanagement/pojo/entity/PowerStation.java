package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("app_powerstation")
public class PowerStation {

    @TableId(type = IdType.AUTO)
    private Long powerStationId;        //电站id
    private String powerStationNumber;  //电站编号
    private String powerStationName;    //电站名称
    private int isStair;                //是否梯级 1:是 2:否
    private int type;       //电站类型(1:蓄水式 2:引水式 3: 径流式)
    private Long reservoirId;           //对应水库id
    private int unit;                   //机组数量
    private String contain;             //装机容量
    private BigDecimal annualAverageRunoff; //年均径流量
    private BigDecimal annualAveragePowerGeneration; //年均发电量
    private String address;             //电站地址
    private BigDecimal maxReferenceTaffic; //最大引用流量
    private BigDecimal minReferenceTaffic; //最小引用流量
}
