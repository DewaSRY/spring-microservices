package com.sdewa.account;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Account Microservices Rest Api Documentation",
				description = "Bank Account microservices Rest Api Documentation ",
				version = "v1",
				contact = @Contact(
						name = "Dewa",
						email = "dewa@Example.com",
						url= "https://google.com"

				),
				license = @License(
						name = "Apache 2.0",
						url= "https://google.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Bank account microservices Rest API Documentation",
				url = "https://www.google.com"
		)
)
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
