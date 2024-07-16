package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private Long id;  // 自增主键id
    private String fileName;
    private String fileUrl;
}
