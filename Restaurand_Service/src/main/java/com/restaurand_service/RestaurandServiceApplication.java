package com.restaurand_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class RestaurandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurandServiceApplication.class, args);
    }

}
