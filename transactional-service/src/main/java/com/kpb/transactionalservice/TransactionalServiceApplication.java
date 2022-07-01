package com.kpb.transactionalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.kpb"})
@EnableFeignClients(basePackages = "com.kpb")
public class TransactionalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionalServiceApplication.class, args);
    }

}
