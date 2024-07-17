package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:tan hao
 * Date: 2024-07-16 21:07
 * Description: 日志文件类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("file_url")
public class FileUrl {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("文件编号")
    private Long id;  // 自增主键id
    @ApiModelProperty("文件名称  以执行算法的时间为文件名")
    private String fileName;
    @ApiModelProperty("文件URL地址  用于下载结果文件")
    private String fileUrl;
}
