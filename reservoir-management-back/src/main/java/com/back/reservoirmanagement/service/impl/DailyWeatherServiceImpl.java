package com.back.reservoirmanagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.back.reservoirmanagement.mapper.DailyWeatherMapper;
import com.back.reservoirmanagement.mapper.MonthlyPrecipitationMapper;
import com.back.reservoirmanagement.pojo.entity.APIWeatherInfo;
import com.back.reservoirmanagement.pojo.entity.DailyWeather;
import com.back.reservoirmanagement.pojo.entity.MonthlyPrecipitation;
import com.back.reservoirmanagement.pojo.vo.TemperatureVO;
import com.back.reservoirmanagement.service.DailyWeatherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DailyWeatherServiceImpl extends ServiceImpl<DailyWeatherMapper, DailyWeather> implements DailyWeatherService {
    // 获取天气数据的网址
    private final String getNowURL = "https://devapi.qweather.com/v7/weather/3d?location=101280101&key=04b74c7c1996405f93bdfcef36e3434b";

    @Autowired
    private MonthlyPrecipitationMapper monthlyPrecipitationMapper;

    @Autowired
    private DailyWeatherMapper dailyWeatherMapper;

    /**
     * 返回实时天气
     *
     * @return
     * @throws Exception
     */
    public APIWeatherInfo getWeather() throws Exception {
        // HTTP请求 天气接口
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getNowURL)
                .build();
        // 从请求体从获取Json数据
        Response response = client.newCall(request).execute();
        String strJson = response.body().string();
        // 进行Json字符串的解析
        JSONArray data = JSON.parseObject(strJson).getJSONArray("daily");
        APIWeatherInfo weather = JSON.parseObject(data.get(0).toString(), APIWeatherInfo.class);
        response.close();

        log.info("实时天气情况:{}", weather);
        return weather;
    }

    /**
     * 获取每年的月降水
     */
    @Override
    public List<Double> getPrecipitationByYear(Integer year) {
        LambdaQueryWrapper<MonthlyPrecipitation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MonthlyPrecipitation::getYear, year);
        List<MonthlyPrecipitation> monthlyPrecipitations = monthlyPrecipitationMapper.selectList(queryWrapper);
        List<Double> precipitations = new ArrayList<>();
        monthlyPrecipitations.forEach(monthlyPrecipitation -> precipitations
                .add(monthlyPrecipitation.getPrecipitation()));
        return precipitations;
    }

    /**
     * 获取某月的气温
     */
    @Override
    public TemperatureVO getTemperature(Integer year, Integer month) {
        List<Integer> minTemperature = dailyWeatherMapper.getMinTemperature(year, month);
        List<Integer> maxTemperature = dailyWeatherMapper.getMaxTemperature(year, month);
        return TemperatureVO
                .builder()
                .minTemperature(minTemperature)
                .maxTemperature(maxTemperature)
                .build();
    }
}