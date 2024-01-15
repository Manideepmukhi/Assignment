package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
     // Creating a CORS configuration
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);// Allowing credentials such as cookies, authorization headers, etc.
        config.addAllowedOrigin("*"); // Allowing all origins
        config.addAllowedHeader("*");// Allowing all headers in the request
        config.addAllowedMethod("*");// Allowing all HTTP methods (GET, POST, PUT, DELETE)
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source); // Creating and return a CorsFilter with the configured source.
    }
}

