package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Author:tan hao
 * Date: 2024-01-26 22:52
 * Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("app_application")
public class Application {
    @TableId(type = IdType.AUTO)
    private Long id;  // 自增主键id
    private Long employeeId;  // 申请用户的id
    private String employeeRealname;
    private Long reservoirId;
    private Long powerStationId;
    private Integer type;
    private String content;
    private LocalDateTime createTime;
    private Integer status;
    private String picture;
}
