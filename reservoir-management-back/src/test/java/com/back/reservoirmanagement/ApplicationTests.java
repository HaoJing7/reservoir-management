package com.back.reservoirmanagement;

import org.junit.jupiter.api.Test;

import java.util.Random;

class ApplicationTests {

    /**
     * 生成日期插入语句
     */
    @Test
    void createTemperature() {
        Random random = new Random();
        for (int i = 1; i <= 31; i++) {
            int max = random.nextInt(5) + 10;
            int min = random.nextInt(5) + 3;
            System.out.println("insert into daily_weather values (null, '2024-01-" + i +"', " + min + " , " + max +" , 10.5);");
        }
    }
}
