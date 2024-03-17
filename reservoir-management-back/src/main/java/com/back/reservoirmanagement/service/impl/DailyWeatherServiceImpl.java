package com.back.reservoirmanagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.back.reservoirmanagement.mapper.DailiWeatherMapper;
import com.back.reservoirmanagement.pojo.entity.APIWeatherInfo;
import com.back.reservoirmanagement.pojo.entity.DailyWeather;
import com.back.reservoirmanagement.service.DailyWeatherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DailyWeatherServiceImpl extends ServiceImpl<DailiWeatherMapper, DailyWeather> implements DailyWeatherService {
    // 获取天气数据的网址
    private final String getNowURL = "https://devapi.qweather.com/v7/weather/3d?location=101280101&key=04b74c7c1996405f93bdfcef36e3434b";

    /**
     * 返回实时天气
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

        log.info("实时天气情况:{}",weather);
        return weather;
    }
}