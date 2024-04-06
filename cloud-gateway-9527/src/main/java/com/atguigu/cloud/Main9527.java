package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.time.ZonedDateTime;

@EnableDiscoveryClient
@SpringBootApplication
public class Main9527 {
    public static void main(String[] args) {
        System.out.println(ZonedDateTime.now());
        SpringApplication.run(Main9527.class,args);
    }
}
