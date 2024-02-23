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
                .email("ganomkar@gmail.com")
                .name("Ganesh Bhat")
                .url("https://www.mywebsite.com");

        License license = new License().name("My License").url("https://www.mylicenseurl.com");

        return new OpenAPI()
                .info(new Info().title("Statistics API")
                        .version("1.0")
                        .description("First API generates the vectors of any given size with vector ID and Second API Calculates the statistics for a given Vector ID")
                        .termsOfService("https://www.terms-of-service-url.com")
                        .contact(contact)
                        .license(license))
                .servers(List.of(devServer, prodServer));
    }
}
