package com.back.reservoirmanagement.pojo.dto;

import lombok.Data;

/**
 * Author:tan hao
 * Date: 2024-01-27 01:28
 * Description:
 */
@Data
public class ApplicationPageQueryDTO {
    private Integer page;
    private Integer pageSize;
    private String name;
    private String beginTime;  // 时间戳
    private String endTime;
    private Integer type;
    private Integer status;
}
