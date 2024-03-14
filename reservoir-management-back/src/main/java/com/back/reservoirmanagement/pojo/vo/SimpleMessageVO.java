package com.back.reservoirmanagement.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "消息的缩略形式")
public class SimpleMessageVO {
    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("消息类型")
    private String type;

    @ApiModelProperty("消息创建时间")
    private LocalDateTime createTime; // 创建时间
}
