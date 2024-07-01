package com.back.reservoirmanagement.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "前端水文数据")
public class HydrologyVO {
    private Integer id;
    private Integer waterLevel;  // 水位
    private Integer runoffVolume;  // 净流量大小
    private Integer sedimentConcentration;  // 含沙量
    private String floodSeason;  // 汛期
    private String presenceOfIceCover;  // 有无结冰期
    private Integer flowVelocity;  // 流速
    private String riverNetworkShape;  // 河流水系形状
    private Integer hydropowerPotential;  //  水能资源蕴藏量
    private String riverNavigationValue;  //  河流航运价值
}
