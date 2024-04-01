package com.back.reservoirmanagement.mapper;

import com.back.reservoirmanagement.pojo.entity.DailyWeather;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DailyWeatherMapper extends BaseMapper<DailyWeather> {
    @Select("SELECT temp_max FROM daily_weather WHERE YEAR(date) = #{param1} AND MONTH(date) = #{param2}")
    List<Integer> getMaxTemperature(Integer year, Integer month);

    @Select("SELECT temp_min FROM daily_weather WHERE YEAR(date) = #{param1} AND MONTH(date) = #{param2}")
    List<Integer> getMinTemperature(Integer year, Integer month);

}
