package com.geophoto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

import java.nio.file.Paths;

/**
 * Web MVC Configuration
 * Configures CORS and Static Resource Handling
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    @Value("${app.upload.dir}")
    private String uploadDir;
    
    /**
     * Configure CORS to allow frontend (localhost:5173) to access the API
     * Note: CORS is also configured in SecurityConfig, this is a fallback
     */
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:*", "http://127.0.0.1:*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
    
    /**
     * Configure Static Resource Handler for uploaded photos
     * Maps /uploads/** URLs to the uploads directory
     */
    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        // Convert relative path to absolute path and ensure it ends with /
        java.nio.file.Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        String uploadLocation = "file:" + uploadPath.toString().replace("\\", "/") + "/";
        
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadLocation);
    }
}

