package com.back.reservoirmanagement.pojo.dto;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * Author:tan hao
 * Date: 2024-01-28 16:54
 * Description:
 */
@Data
public class ReservoirPageDTO {
    private Integer page;
    private Integer pageSize;
    private String number;  // 水库编号
    private String name;  // 水库名称
    private Integer type;  // 水库类型
}
