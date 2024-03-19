package com.back.reservoirmanagement.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Random;

/**
 * Author:tan hao
 * Date: 2024-03-18 17:31
 * Description: 水库运行数据  采用模拟的形式
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PowerStationData {
    private int id;
    private Date date;
    private int[] powerGeneration; // 单位：kWh
    private float[] waterLevel; // 单位：m
    private float inflowFlowRate; // 单位：m^3/s
    private float outflowFlowRate; // 单位：m^3/s
    private float pressure; // 单位：Pa
    private float speed; // 单位：rpm
    private float voltage; // 单位：V
    private float frequency; // 单位：Hz
    private float gridLoad; // 单位：kW

    // 随机生成PowerstationData对象

    // 一天的小时数
    private static final int HOURS_IN_A_DAY = 24;
    // 生成随机数的最小值和最大值
    private static final int MIN_POWER_GENERATION = 5000; // 最小发电量（kWh）
    private static final int MAX_POWER_GENERATION = 10000; // 最大发电量（kWh）
    private static final float MIN_WATER_LEVEL = 30.0f; // 最小水位（m）
    private static final float MAX_WATER_LEVEL = 50.0f; // 最大水位（m）
    private static final float MIN_FLOW_RATE = 0.1f; // 最小流量（m^3/s）
    private static final float MAX_FLOW_RATE = 10.0f; // 最大流量（m^3/s）
    private static final float MIN_PRESSURE = 50000.0f; // 最小压力（Pa）
    private static final float MAX_PRESSURE = 100000.0f; // 最大压力（Pa）
    private static final float MIN_SPEED = 900.0f; // 最小转速（rpm）
    private static final float MAX_SPEED = 1200.0f; // 最大转速（rpm）
    private static final float MIN_VOLTAGE = 200.0f; // 最小电压（V）
    private static final float MAX_VOLTAGE = 240.0f; // 最大电压（V）
    private static final float MIN_FREQUENCY = 49.5f; // 最小频率（Hz）
    private static final float MAX_FREQUENCY = 50.5f; // 最大频率（Hz）
    private static final float MIN_GRID_LOAD = 500.0f; // 最小电网负荷（kW）
    private static final float MAX_GRID_LOAD = 10000.0f; // 最大电网负荷（kW）

    public static PowerStationData generateData() {
        Random random = new Random();
        PowerStationData data = new PowerStationData();
        data.setId(random.nextInt(1000)); // 随机生成 ID
        data.setDate(new Date()); // 使用当前时间作为日期

        // 生成一天中每个小时的随机发电量和水位数据
        int[] powerGeneration = new int[HOURS_IN_A_DAY];
        float[] waterLevel = new float[HOURS_IN_A_DAY];
        for (int i = 0; i < HOURS_IN_A_DAY; i++) {
            powerGeneration[i] = random.nextInt(MAX_POWER_GENERATION - MIN_POWER_GENERATION + 1) + MIN_POWER_GENERATION;
            waterLevel[i] = random.nextFloat() * (MAX_WATER_LEVEL - MIN_WATER_LEVEL) + MIN_WATER_LEVEL;
        }
        data.setPowerGeneration(powerGeneration);
        data.setWaterLevel(waterLevel);

        // 生成其它随机数作为其它数据的值
        data.setInflowFlowRate(random.nextFloat() * (MAX_FLOW_RATE - MIN_FLOW_RATE) + MIN_FLOW_RATE);
        data.setOutflowFlowRate(random.nextFloat() * (MAX_FLOW_RATE - MIN_FLOW_RATE) + MIN_FLOW_RATE);
        data.setPressure(random.nextFloat() * (MAX_PRESSURE - MIN_PRESSURE) + MIN_PRESSURE);
        data.setSpeed(random.nextFloat() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
        data.setVoltage(random.nextFloat() * (MAX_VOLTAGE - MIN_VOLTAGE) + MIN_VOLTAGE);
        data.setFrequency(random.nextFloat() + MIN_FREQUENCY);
        data.setGridLoad(random.nextFloat() * (MAX_GRID_LOAD - MIN_GRID_LOAD) + MIN_GRID_LOAD);

        return data;
    }
}
