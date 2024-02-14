package com.assessment;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${your.app.dev-url}")
    private String devUrl;

    @Value("${your.app.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        Server devServer = new Server().url(devUrl).description("Development Server");
        Server prodServer = new Server().url(prodUrl).description("Production Server");

        Contact contact = new Contact()
                .email("your.email@example.com")
                .name("Your Name")
                .url("https://www.yourwebsite.com");

        License license = new License().name("Your License").url("https://www.yourlicenseurl.com");

        return new OpenAPI()
                .info(new Info().title("Your API Title")
                        .version("1.0")
                        .description("Your API Description")
                        .termsOfService("https://www.terms-of-service-url.com")
                        .contact(contact)
                        .license(license))
                .servers(List.of(devServer, prodServer));
    }
}
