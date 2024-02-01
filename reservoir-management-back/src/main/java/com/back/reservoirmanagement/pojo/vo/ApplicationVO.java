package com.back.reservoirmanagement.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查看历史申请返回的数据")
public class ApplicationVO implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;  // 自增主键id
    private String reservoirName;  // 水库名
    private String powerStationName; // 电站名
    private String type;  // 申请类型  1变压器故障  2调速器故障  3水轮机故障  4发电机组故障  0其它
    private String status;  //申请状态
    private String content;  // 文件内容
    private LocalDateTime createTime;  // 创建时间
}
