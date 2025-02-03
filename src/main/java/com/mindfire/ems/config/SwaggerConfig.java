package com.mindfire.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI openAPIDefinition() {
        Contact contact = new Contact();
        contact.setEmail("rashmin@mindfire.com");
        contact.setName("Rashmi Nayak");

        return new OpenAPI().info(
                new Info().title("EMS").version("1.0").contact(contact)
                        .description("A complete employee management portal"));
    }
}
