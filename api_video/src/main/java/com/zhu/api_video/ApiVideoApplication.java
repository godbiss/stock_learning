package com.zhu.api_video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiVideoApplication.class, args);
    }

}
