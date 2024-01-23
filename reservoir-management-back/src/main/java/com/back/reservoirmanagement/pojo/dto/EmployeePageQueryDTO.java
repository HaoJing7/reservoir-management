package com.back.reservoirmanagement.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Author:tan hao
 * Date: 2024-01-23 12:23
 * Description:
 */
@Data
public class EmployeePageQueryDTO {
    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("页码")
    private int page;

    @ApiModelProperty("每页显示记录数")
    private int pageSize;
}
