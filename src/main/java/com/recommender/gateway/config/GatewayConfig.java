package com.recommender.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()


                //rutas del microservicio de user
                .route("user-delete", r -> r
                        .path("/api/user/**")
                        .and()
                        .method(HttpMethod.DELETE)
                        .uri("lb://user"))
                .route("user-post", r -> r
                        .path("/api/user")
                        .and()
                        .method(HttpMethod.POST)
                        .uri("lb://user"))
                .route("user-patch", r -> r
                        .path("/api/user/**")
                        .and()
                        .method(HttpMethod.PATCH)
                        .uri("lb://user"))

                //rutas del microservicio de clothing-item
                .route("clothing-item", r-> r
                        .path("/api/clothing-item/**")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://clothing-item-service"))
                .route("clothing-item-delete", r -> r
                        .path("/api/clothing-item/**")
                        .and()
                        .method(HttpMethod.DELETE)
                        .uri("lb://clothing-item-service"))
                .route("clothing-item-post", r -> r
                        .path("/api/clothing-item")
                        .and()
                        .method(HttpMethod.POST)
                        .uri("lb://clothing-item-service"))
                .route("clothing-item-patch", r -> r
                        .path("/api/clothing-item/**")
                        .and()
                        .method(HttpMethod.PATCH)
                        .uri("lb://clothing-item-service"))

                // rutas del microservicio de recommender
                .route("recommender", r-> r
                        .path("/api/items/discount")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://recommender-service"))
                .route("recommender", r-> r
                        .path("/api/item/**")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://recommender-service"))
                .route("recommender", r-> r
                        .path("/api/user/**")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://recommender-service"))
                .route("recommender", r-> r
                        .path("/api/items/filtered/**")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://recommender-service"))





                .build();
    }
}
