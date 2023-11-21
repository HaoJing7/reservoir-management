package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:tan hao
 * Date: 2023-10-22 23:33
 * Description:
 * 管理员实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("admin_user")
public class Admin {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String problem;
    private String answer;
}
