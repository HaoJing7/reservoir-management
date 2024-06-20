package com.back.reservoirmanagement.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author:tan hao
 * Date: 2024-06-18 22:09
 * Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashBoardDataVO {
    private Long reservoir;
    private Long station;
    private Long employee;
    private Long waiting;
    private Long processing;
    private List<Integer> list;
    private Integer sumPower;
    private Integer avgPower;
    private Integer minPower;
    private Integer maxPower;
}
