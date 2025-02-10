package com.ilelli.apiGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    private final ServiceConfig serviceConfig;

    public ApiGatewayConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/user/**")
                        .uri(serviceConfig.getUserServiceUrl()))
                .route("message-service", r -> r.path("/message/**")
                        .uri(serviceConfig.getMessageServiceUrl()))
                .build();
    }

}
