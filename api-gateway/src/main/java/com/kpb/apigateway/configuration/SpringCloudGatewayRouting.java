package com.kpb.apigateway.configuration;

import com.kpb.apigateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayRouting {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes() //dynamic routing
                .route("authId", r->r.path("/auth/**").filters(f->f.filter(filter)).uri("lb://AUTH-SERVICE"))
                .route("accommodationId", r->r.path("/accommodation/**").filters(f->f.filter(filter)).uri("lb://ACCOMMODATION-SERVICE"))
                .route("allocationId", r->r.path("/allocation/**").filters(f->f.filter(filter)).uri("lb://ALLOCATION-SERVICE"))
                .route("creditId", r->r.path("/credit/**").filters(f->f.filter(filter)).uri("lb://CREDIT-SERVICE"))
                .route("fileUploadId", r->r.path("/file/**").filters(f->f.filter(filter)).uri("lb://FILE-UPLOAD-SERVICE"))
                .route("informationId", r->r.path("/information/**").filters(f->f.filter(filter)).uri("lb://INFORMATION-SERVICE"))
                .route("insuranceId", r->r.path("/insurance/**").filters(f->f.filter(filter)).uri("lb://INSURANCE-SERVICE"))
                .route("marketId", r->r.path("/market/**").filters(f->f.filter(filter)).uri("lb://MARKET-SERVICE"))
                .route("memberId", r->r.path("/members/**").filters(f->f.filter(filter)).uri("lb://MEMBER-SERVICE"))
                .route("planningId", r->r.path("/planning/**").filters(f->f.filter(filter)).uri("lb://PLANNING-SERVICE"))
                .route("scholarshipId", r->r.path("/scholarship/**").filters(f->f.filter(filter)).uri("lb://SCHOLARSHIP-SERVICE"))
                .route("transactionId", r->r.path("/transaction/**").filters(f->f.filter(filter)).uri("lb://TRANSACTIONAL-SERVICE"))
                .build();
    }
}
