package com.apiGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("food-service", r -> r
                        .path("/foods/**")
                        .filters(f -> f.rewritePath("/foods/(?<segment>.*)", "/${segment}")
                                . circuitBreaker( c-> c.setName("circuitBreakerFood")
                                        .setFallbackUri("forward:/circuitBreaker/fallback")
                                ))

                        .uri("lb://FOOD-SERVICE"))

                .route("restaurant-service", r -> r
                        .path("/restaurant/**")
                        .filters(f -> f.rewritePath("/restaurant/(?<segment>.*)", "/${segment}")
                        . circuitBreaker( c-> c.setName("circuitBreakerRestaurant")))
                        .uri("lb://RESTAURANT-SERVICE"))

                .build();
    }
}
