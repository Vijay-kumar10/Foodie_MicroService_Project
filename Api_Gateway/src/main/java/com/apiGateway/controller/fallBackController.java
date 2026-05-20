package com.apiGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class fallBackController {

    //for this we use reactive web-flux approach
    @RequestMapping("circuitBreaker/fallback")
    public Mono<String> circuitBreakerFallback() {
        return Mono.just("Food service is down. Contact to Service Provider.");
    }

}
