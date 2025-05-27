package com.recommender.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("recommender", r-> r
                        .path("/api/get/item/**")
                        .uri("lb://recommender-service"))
                .route("recommender", r-> r
                        .path("/api/get/user/**")
                        .uri("lb://recommender-service"))
                .build();
    }
}
