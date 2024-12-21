package com.conceptile.flowchart.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Flowchart Management API").version("1.0")
				.description("API documentation for managing flowcharts")
				.contact(new Contact().name("Vivekanand").email("vivekanand.18461@knit.ac.in")));
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("flowchart-api").pathsToMatch("/flowcharts/**").build();
	}
}