package com.back.reservoirmanagement.controller.common;

import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.pojo.entity.APIWeatherInfo;
import com.back.reservoirmanagement.service.DailyWeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping({"/weather", "/admin/weather"})
public class WeatherController {

    @Resource
    private DailyWeatherService weatherService;

    /**
     * 获取实时天气
     * @return
     */
    @GetMapping
    public Result<?> getWeather()  {
        APIWeatherInfo weathers = null;
        try {
            weathers = weatherService.getWeather();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(weathers);
    }

    /**
     * 获取某年的月降水信息
     */
    @GetMapping("/precipitation/{year}")
    public Result<?> getPrecipitationByYear(@PathVariable Integer year) {
        return Result.success(weatherService.getPrecipitationByYear(year));
    }

    /**
     * 获取某月的日气温信息
     */
    @GetMapping("/temperature/{year}/{month}")
    public Result<?> getTemperature(@PathVariable Integer year, @PathVariable Integer month) {
        return Result.success(weatherService.getTemperature(year, month));
    }

}
