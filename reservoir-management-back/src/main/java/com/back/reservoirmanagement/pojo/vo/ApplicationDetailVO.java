package com.back.reservoirmanagement.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Author:tan hao
 * Date: 2024-01-28 12:19
 * Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDetailVO {
    // 员工信息
    private String realname;
    private String username;
    private String gender;
    private String phone;
    private String homeAddress;
    private String workPlace;
    private String icon;
    private LocalDateTime createTime;
    // 水库名称
    private String reservoirName;
    // 电站名称
    private String powerStationName;
    // 申请类型
    private String type;
    // 申请内容
    private String content;
    // 申请时间
    private LocalDateTime applicationCreateTime;
    // 当前的状态
    private String status;
    // 附件url
    private String picture;
}
