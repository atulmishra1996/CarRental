package com.example.CarRentals.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI carRentalsOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Cars Rental API")
                .description("API for managing cars rental")
                .version("1.0.0"));
    }
}
