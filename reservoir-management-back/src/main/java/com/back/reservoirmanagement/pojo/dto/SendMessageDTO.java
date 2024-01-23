package com.back.reservoirmanagement.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * Author:tan hao
 * Date: 2024-01-23 18:31
 * Description:
 */
@Data
public class SendMessageDTO {
    private List<Long> employeeIds;
    private String content;
    private Integer level;
}
