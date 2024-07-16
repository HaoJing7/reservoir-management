package com.back.reservoirmanagement.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMessageListVO {
    @ApiModelProperty("主键值")
    private Long count;

    @ApiModelProperty("标题")
    private List<SimpleMessageVO> list;
}
