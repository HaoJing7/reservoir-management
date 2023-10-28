package com.back.reservoirmanagement;



import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MyBackAdminTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBackAdminTemplateApplication.class, args);
        log.info("项目启动完成");
    }
}
