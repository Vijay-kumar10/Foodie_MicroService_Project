package com.apiGateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Configuration
public class ApiGateWayConfig {

    //if you want to limit based on user IP
    @Bean
    public KeyResolver keyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getRemoteAddress()        //Used for IP address
                        .getAddress()
                        .getHostAddress()
                        );
    }

    //Rate Limitor Bean
    @Bean
    public RedisRateLimiter rateLimiter() {
        return new RedisRateLimiter(1, 60, 20);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()

                .route("food-service", r -> r
                        .path("/foods/**")
                        .filters(f -> f
                                .rewritePath("/foods/(?<segment>.*)", "/${segment}")
                                .circuitBreaker(c -> c
                                        .setName("circuitBreakerFood")
                                        .setFallbackUri("forward:/circuitBreaker/fallback")
                                )
                                .requestRateLimiter(rateConfig->rateConfig
                                        .setRateLimiter(rateLimiter())
                                        .setKeyResolver(keyResolver())
                                )
                        )
                        .uri("lb://FOOD-SERVICE")
                )

                .route("restaurant-service", r -> r
                        .path("/restaurant/**")
                        .filters(f -> f
                                .rewritePath("/restaurant/(?<segment>.*)", "/${segment}")
                                .retry(retryConfig -> retryConfig
                                        .setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setStatuses(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .setBackoff(
                                                Duration.ofMillis(100),
                                                Duration.ofMillis(800),
                                                2,
                                                true
                                        )
                                )
                        )
                        .uri("lb://RESTAURANT-SERVICE")
                )

                .build();
    }
}
