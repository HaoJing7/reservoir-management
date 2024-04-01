package com.back.reservoirmanagement.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author:tan hao
 * Date: 2024-03-24 10:09
 * Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureVO {
    // 最高气温列表
    private List<Integer> maxTemperature;
    // 最低气温列表
    private List<Integer> minTemperature;
}
