package com.back.reservoirmanagement.pojo.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户提交申请传入数据")

public class ApplicationSubmitDTO {

    @ApiModelProperty("员工名")
    private String employeeRealname;
    @ApiModelProperty("员工id 后端获取")
    private Long employeeId;
    @ApiModelProperty("电站的id")
    private Long powerStationId; // 电站id
    @ApiModelProperty("水库的id")
    private Long reservoirId;  // 水库id
    @ApiModelProperty("申请类型 1变压器故障  2调速器故障  3水轮机故障  4发电机组故障  0其它")
    private int type;  // 申请类型  1变压器故障  2调速器故障  3水轮机故障  4发电机组故障  0其它
    @ApiModelProperty("申请内容")
    private String content;

}
