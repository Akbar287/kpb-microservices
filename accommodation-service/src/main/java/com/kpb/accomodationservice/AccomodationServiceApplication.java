package com.kpb.accomodationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccomodationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccomodationServiceApplication.class, args);
    }

}
