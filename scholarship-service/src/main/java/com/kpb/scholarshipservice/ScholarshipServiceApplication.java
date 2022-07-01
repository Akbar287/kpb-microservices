package com.kpb.scholarshipservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ScholarshipServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScholarshipServiceApplication.class, args);
	}

}
