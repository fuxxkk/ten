package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringBootApplication
@MapperScan("com.mapper")
@EnableFeignClients
public class QAApplication {
    public static void main(String[] args) {
        SpringApplication.run(QAApplication.class, args);
        System.out.println("http://localhost:9003/swagger-ui.html");
    }
}
