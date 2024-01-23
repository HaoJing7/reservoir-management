package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;  // 自增主键id
    private Integer level;  // 消息的类型  1为通知消息 2为提示消息 3为紧急消息
    private String content;  // 消息的内容
    private Long employeeId;  // 接收该消息的用户
}
