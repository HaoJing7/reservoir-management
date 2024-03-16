package com.back.reservoirmanagement.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "水库简单数据")
public class ReservoirSimpleVO implements Serializable {

    @ApiModelProperty("水库id")
    private Long id;

    @ApiModelProperty("水库名称")
    private String name;

}
