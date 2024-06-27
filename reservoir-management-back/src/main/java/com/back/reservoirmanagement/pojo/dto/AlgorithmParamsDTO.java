package com.back.reservoirmanagement.pojo.dto;

import lombok.Data;

/**
 * Author:tan hao
 * Date: 2024-06-24 18:55
 * Description:
 */
@Data
public class AlgorithmParamsDTO {
    private Integer selectedAlgorithm;  // 算法选择
    private Integer particleCount;  // 粒子数量
    private Integer iterationCount;  //  迭代次数
}
