package com.back.reservoirmanagement.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("daily_weather")
public class DailyWeather {
    private Long id;
    private LocalDateTime date;
    private Integer tempMax;//当天最高温度
    private Integer tempMin;//当天最低温度
    private Double precipitation;//当天总降水量，默认单位：毫米
}
