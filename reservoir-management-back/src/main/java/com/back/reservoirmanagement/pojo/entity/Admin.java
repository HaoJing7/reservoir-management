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
 * Date: 2023-10-22 23:33
 * Description:
 * 管理员实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("admin_user")
public class Admin {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private Integer status;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private Long create_user;
    private Long update_user;
}
