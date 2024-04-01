package com.back.reservoirmanagement.task;

import com.back.reservoirmanagement.pojo.entity.APIWeatherInfo;
import com.back.reservoirmanagement.pojo.entity.DailyWeather;
import com.back.reservoirmanagement.pojo.entity.MonthlyPrecipitation;
import com.back.reservoirmanagement.service.DailyWeatherService;
import com.back.reservoirmanagement.service.MonthlyPrecipitationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class Task {

    @Resource
    private DailyWeatherService dailyWeatherService;

    @Resource
    private MonthlyPrecipitationService monthlyPrecipitationService;

    /**
     * 定时任务 每天23点59分记录一次当天最高最低温
     */
    @Scheduled(cron = "0 59 23 * * ? ")
    public void dailyMaxAndMinTemperature() throws Exception{
        APIWeatherInfo WeatherInfo = dailyWeatherService.getWeather();
        DailyWeather weather=new DailyWeather();
        // 保存数据库表中需要的信息
        BeanUtils.copyProperties(WeatherInfo, weather);
        weather.setDate(LocalDateTime.now());
        dailyWeatherService.save(weather);
        log.info("已写入:{}",new Date(),weather);
    }

    /**
     * 定时任务 每月末的23点59分统计月降水总量
     * @throws Exception
     */
    @Scheduled(cron = "0 59 23 L * ?")
    public void monthlyPrecipitation() throws Exception{
        // 获取月初月末时间
        LocalDateTime end = LocalDateTime.now().minusMonths(1).withNano(0);
        LocalDateTime start = end.with(TemporalAdjusters.firstDayOfMonth()).withSecond(0).withMinute(0).withHour(0);
        log.info("时间:{}--->{}",start,end);
        // 构造条件获取本月的全部降水数据
        LambdaQueryWrapper<DailyWeather> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.between(DailyWeather::getDate,start,end);
        List<DailyWeather> list = dailyWeatherService.list(queryWrapper);
        log.info("当月数据:{}",list);
        // 计算月总降水量并保存
        MonthlyPrecipitation total=new MonthlyPrecipitation();
        for (DailyWeather dailyWeather : list) {
            Double precip=total.getPrecipitation();
            total.setPrecipitation(precip+dailyWeather.getPrecipitation());
        }
        // 其他参数
        total.setYear(end.getYear());
        total.setMonth(end.getMonthValue());
        monthlyPrecipitationService.save(total);
    }
}
