package com.example.Config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class AppConfig {

	@Bean
	public OpenAPI myOpenAPI() {

		Server devServer = new Server();
		devServer.setUrl("https://www.rjay-dev.com");
		devServer.setDescription("Server URL in Development environment");

		Contact contact = new Contact();
		contact.setEmail("bezkoder@gmail.com");
		contact.setName("BezKoder");
		contact.setUrl("https://www.rjaytech.com");

		License mitLicense = new License().name("MIT License").url("https://rjay.com/");

		Info info = new Info().title("Tutorial Management API").version("1.0").contact(contact)
				.description("This API exposes endpoints to manage tutorials.")
				.termsOfService("https://www.bezkoder.com/terms").license(mitLicense);

		return new OpenAPI().info(info).servers(List.of(devServer));
	}
}
