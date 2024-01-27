package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("app_application")
public class Application {

    @TableId(type = IdType.AUTO)
    private Long id;  // 自增主键id
    private Long employeeId;  // 发起申请用户的id
    private String employeeRealname;  // 员工名字
    private Long reservoirId;  // 水库id
    private Long powerStationId; // 电站id
    private int type;  // 申请类型  1变压器故障  2调速器故障  3水轮机故障  4发电机组故障  0其它
    private int status;  //申请状态、
    private String content;  // 文件内容
    private LocalDateTime createTime;  // 创建时间
}
