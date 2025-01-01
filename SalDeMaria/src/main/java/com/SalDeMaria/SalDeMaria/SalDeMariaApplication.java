package com.SalDeMaria.SalDeMaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger OpenApi", version = "1", description = "Inserindo o Swagger com OpenApi"))
public class SalDeMariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalDeMariaApplication.class, args);
	}

}
