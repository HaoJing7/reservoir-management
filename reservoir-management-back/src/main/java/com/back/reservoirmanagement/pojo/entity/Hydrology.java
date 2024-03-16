package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:tan hao
 * Date: 2024-03-16 20:11
 * Description: 水文数据类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@TableName("hydrology")
public class Hydrology {
    private Integer id;
    private Integer waterLevel;  // 水位
    private Integer runoffVolume;  // 净流量大小
    private Integer sedimentConcentration;  // 含沙量
    private Integer floodSeason;  // 汛期
    private Integer presenceOfIceCover;  // 有无结冰期
    private Integer flowVelocity;  // 流速
    private Integer riverNetworkShape;  // 河流水系形状
    private Integer hydropowerPotential;  //  水能资源蕴藏量
    private Integer riverNavigationValue;  //  河流航运价值
}
