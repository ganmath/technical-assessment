package com.assessment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for simplicity (consider re-enabling for production)
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll() // Allow unauthenticated access to Swagger UI
                .requestMatchers("/api/vectors/**").authenticated() // Secure all endpoints under /api/vectors
            )
            .httpBasic(); // Use HTTP Basic Authentication

        return http.build();
    }
}
