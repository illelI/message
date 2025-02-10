package com.ilelli.apiGateway;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "services")
@Getter
@Setter
public class ServiceConfig {
    private String userServiceUrl;
    private String messageServiceUrl;
}
