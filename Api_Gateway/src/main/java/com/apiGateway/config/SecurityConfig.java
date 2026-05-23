package com.apiGateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
            http.cors(e->e.disable()).csrf(e->e.disable());
            http.authorizeExchange(e->
                    e.pathMatchers(HttpMethod.GET).permitAll()
                            .pathMatchers("/foods/**").hasRole("ADMIN")
                            .pathMatchers(HttpMethod.POST,"/restaurant/**").hasRole("ADMIN")
                            .anyExchange() .authenticated()
                    )
                    .oauth2ResourceServer(config->
                            config.jwt(Customizer.withDefaults()))
            ;


        return  http.build();
    }
}
