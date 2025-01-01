package com.SalDeMaria.SalDeMaria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customAPi(){
        return new OpenAPI().info(new Info().title("Swagger API").version("1.0.0"));
    }

}
