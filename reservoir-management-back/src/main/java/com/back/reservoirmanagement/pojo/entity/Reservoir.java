package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("app_reservoir")
public class Reservoir {
    private String number;   //水库编号
    private String name;     //水库名称
    private String address;    //水库地址
    private String usableCapacity;  //有效容量
    private String designCapacity;  //设计容量
    private String maxCapacity;     //最大容量
    private String minCapacity;     //最小容量
    private String deadCapacity;    //死容量
    private String normalWaterLevel;    //正常水位
    private String meixunWaterLevel;    //梅汛期水位
    private String typhoonWaterLevel;   //台风期水位
    private String floodWaterLevel;     //洪水期水位
    private String maxWaterLevel;       //最高水位
    private String deadWaterLevel;      //死水位
}
