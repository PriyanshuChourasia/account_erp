package com.codymitra.auth_service.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtConfig(
    String secret,
    String accessExpiration,
    String refreshExpiration
) {}
