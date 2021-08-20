package com.example.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAsync
@EnableSwagger2
@EnableScheduling
@EnableTransactionManagement
@MapperScan("com.example.dao")
@SpringBootApplication(scanBasePackages ={"com.example.core","com.example.api","com.example.rest"})
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
