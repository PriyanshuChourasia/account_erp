package com.codymitra.auth_service.configs;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Account ERP API")
                        .version("1.0.0"))
                .servers(java.util.List.of(
                        new Server()
                                .url("http://localhost:8060")
                                .description("Local development server")
                ));
    }
}
