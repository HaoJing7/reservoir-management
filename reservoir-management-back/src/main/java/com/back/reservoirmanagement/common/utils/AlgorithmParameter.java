package com.back.reservoirmanagement.common.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author:tan hao
 * Date: 2024-07-15 20:46
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlgorithmParameter {
    // 算法类型  0表示MOEA/D ；1表示NSGA-II
    private Integer algorithm;
    // 粒子数量  30-200
    private Integer particleCount;
    // 迭代次数  100-300
    private Integer iterationCount;

    // 第一行参数（需水量）
    private List<Double> monthlyDemand;
    // 第二行参数（入库流量）
    private List<Double> monthlyInflow;
    // 第三行参数（水库参数信息）
    private List<Double> reservoirParams;
}
