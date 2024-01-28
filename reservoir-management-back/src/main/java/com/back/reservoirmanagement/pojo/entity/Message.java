package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Author:tan hao
 * Date: 2024-01-23 15:15
 * Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("app_message")
public class Message {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("消息编号")
    private Long id;  // 自增主键id
    @ApiModelProperty("消息类型")
    private Integer level;  // 消息的类型  1为通知消息 2为提示消息 3为紧急消息
    @ApiModelProperty("消息内容")
    private String content;  // 消息的内容
    @ApiModelProperty("接收消息的用户的id")
    private Long employeeId;  // 接收该消息的用户
    @ApiModelProperty("是否完成标志 0 已完成  1 未完成")
    private Integer finished;  // 是否完成  0 已完成  1 未完成
    @ApiModelProperty("消息编号 0未读  1已读")
    private Integer checked;  // 是否已读  0未读  1已读
    @ApiModelProperty("消息创建时间")
    private LocalDateTime createTime; // 创建时间
}
