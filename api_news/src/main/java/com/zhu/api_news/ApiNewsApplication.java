package com.zhu.api_news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients
@EnableDiscoveryClient
public class ApiNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiNewsApplication.class, args);
    }

}
