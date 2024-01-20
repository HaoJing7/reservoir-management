package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("app_user")
public class User implements Serializable {

    @TableId(type=IdType.AUTO)
    private Long id;
    private String realname;
    private String username;
    private String gender;
    private String password;
    private String phone;
    private String homeAddress;
    private String workPlace;
    private String icon;
    private LocalDateTime createTime;
}
