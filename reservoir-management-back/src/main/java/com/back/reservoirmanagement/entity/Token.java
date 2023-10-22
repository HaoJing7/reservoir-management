package com.back.reservoirmanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:tan hao
 * Date: 2023-10-23 00:05
 * Description:
 */
@Data
@TableName("admin_token")
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String token;
}
