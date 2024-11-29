package com.sdewa.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.sdewa.cards.controller") })
@EnableJpaRepositories("com.sdewa.cards.repository")
@EntityScan("com.sdewa.cards.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
	info = @Info(
		title = "Card microservice REST APi Documentation",
		description = "Card microservices REST API Documentation",
		version = "v1",
		contact = @Contact(
			name = "Dewa",
			email = "dewa@Example.com",
			url = "https://www.google.com"
		),
		license = @License(
			name = "Apache 2.0",
			url = "https://www.google.com"
		)
	),
	externalDocs = @ExternalDocumentation(
		description = "Bank card microservice REST APi Documentation",
		url = "https://www.google.com"
	)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
