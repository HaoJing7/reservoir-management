package com.back.reservoirmanagement.service;

import com.back.reservoirmanagement.pojo.entity.APIWeatherInfo;
import com.back.reservoirmanagement.pojo.entity.DailyWeather;
import com.baomidou.mybatisplus.extension.service.IService;


public interface DailyWeatherService extends IService<DailyWeather> {

    /**
     * 获取实时天气数据
     * @return
     * @throws Exception
     */
    public APIWeatherInfo getWeather() throws Exception;
}