package com.zhu.api_user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.zhu.api_user.mapper")
@SpringBootApplication
@EnableDiscoveryClient
public class ApiUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiUserApplication.class, args);
    }

}
