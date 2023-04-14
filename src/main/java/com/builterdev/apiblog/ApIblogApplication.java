package com.builterdev.apiblog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Blog API",
                description = "Spring boot Rest API",
                version = "1.0",
                contact = @Contact(
                        name = "Aaron",
                        email = "aaron@gmail.com",
                        url = "http://google.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://google.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Blog App Documentation",
                url = "http://github.com/AaronHapy"
        )
)
public class ApIblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApIblogApplication.class, args);
    }

}
