package com.back.reservoirmanagement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableCaching
@Slf4j
public class Application {

    // TODO 首页编写
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("服务已经启动");

    }
}
