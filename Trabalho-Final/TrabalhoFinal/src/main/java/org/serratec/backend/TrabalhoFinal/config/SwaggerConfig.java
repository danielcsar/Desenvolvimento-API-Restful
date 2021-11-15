package org.serratec.backend.TrabalhoFinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.serratec.backend.TrabalhoFinal.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());	
	}
	
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder()
		.title("API Ecommerce SerraTec")
		.description("API de site ecommerce do Grupo 1, Residência 2021.2, Serratec")
		.termsOfServiceUrl("/service.html")
		.version("0.0.0")
		.contact(new Contact("Ecommerce", "www.serratec.org", "grupo01testeapi@gmail.com"))
		.build();
		
		return apiInfo;
	}
}
