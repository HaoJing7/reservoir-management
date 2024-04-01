package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("monthly_precipitation")
public class MonthlyPrecipitation {
    private Long id;
    private Integer year;//年份
    private Integer month;//月份
    private Double precipitation;//月总降水量，默认单位：毫米
}
