package com.back.reservoirmanagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.back.reservoirmanagement.mapper.DailiWeatherMapper;
import com.back.reservoirmanagement.mapper.MonthlyPrecipitationMapper;
import com.back.reservoirmanagement.pojo.entity.APIWeatherInfo;
import com.back.reservoirmanagement.pojo.entity.DailyWeather;
import com.back.reservoirmanagement.pojo.entity.MonthlyPrecipitation;
import com.back.reservoirmanagement.service.DailyWeatherService;
import com.back.reservoirmanagement.service.MonthlyPrecipitationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MonthlyPrecipitationServiceImpl extends ServiceImpl<MonthlyPrecipitationMapper, MonthlyPrecipitation> implements MonthlyPrecipitationService {

}