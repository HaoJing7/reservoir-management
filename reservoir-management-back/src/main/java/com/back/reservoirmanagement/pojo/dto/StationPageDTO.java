package com.back.reservoirmanagement.pojo.dto;

import lombok.Data;

/**
 * Author:tan hao
 * Date: 2024-01-28 16:54
 * Description:
 */
@Data
public class StationPageDTO {
    private Integer page;
    private Integer pageSize;
    private String number;  // 电站编号
    private String name;  // 电站名称
    private Integer type;  // 电站类型
}
