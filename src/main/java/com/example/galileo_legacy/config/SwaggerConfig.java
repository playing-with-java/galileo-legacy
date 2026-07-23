package com.example.galileo_legacy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Galileo Legacy API")
                        .description("API REST para gestionar productos y usuarios")
                        .version("1.0.0")
                        .termsOfService("Terms of service")
                        .contact(new Contact()
                                .name("Galileo Legacy")
                                .url("https://example.com")
                                .email("contact@example.com"))
                        .license(new License()
                                .name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}

