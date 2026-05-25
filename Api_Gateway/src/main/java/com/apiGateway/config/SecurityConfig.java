package com.apiGateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private RoleConverter roleConverter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
            http.cors(e->e.disable()).csrf(e->e.disable());
            http.authorizeExchange(e->
                    e.pathMatchers(HttpMethod.GET).permitAll()
                            .pathMatchers("/foods/**").hasRole("ADMIN")
                            .pathMatchers("/restaurant/**").hasRole("ADMIN")
                            .anyExchange() .authenticated()
                    )
                    .oauth2ResourceServer(config->
                            config.jwt(
                                    jwt->jwt.jwtAuthenticationConverter(roleExtractor())
                            )
                    )
            ;

        return  http.build();
    }

    public Converter<Jwt, Mono<AbstractAuthenticationToken>> roleExtractor() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(roleConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }
}
