package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.pojo.entity.APIWeatherInfo;
import com.back.reservoirmanagement.pojo.entity.DailyWeather;
import com.back.reservoirmanagement.pojo.vo.TemperatureVO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;


public interface DailyWeatherService extends IService<DailyWeather> {

    /**
     * 获取实时天气数据
     */
    APIWeatherInfo getWeather() throws Exception;

    /**
     * 获取某年降水量
     */
    List<Double> getPrecipitationByYear(Integer year);

    /**
     * 获取某月的气温数据
     */
     TemperatureVO getTemperature(Integer year, Integer month);
}