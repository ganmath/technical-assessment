/**
 * The StatisticsApiApplication class serves as the entry point for the Spring Boot application.
 * It is annotated with @SpringBootApplication, indicating that it is the main configuration class
 * and enabling various configurations, component scanning, and auto-configuration features.
 */
package com.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StatisticsApiApplication {

    /**
     * The main method starts the Spring Boot application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApiApplication.class, args);
    }
}