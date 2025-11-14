package com.example.replytech.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SwaggerConfig - Configuration for Swagger/OpenAPI documentation
 * 
 * Configures OpenAPI 3.0 specification for API documentation with
 * custom branding, contact information, and API details.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configure OpenAPI bean with custom API information
     * 
     * This bean provides comprehensive API documentation including
     * title, description, version, contact, and license information.
     * 
     * @return OpenAPI configuration
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(java.util.List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local Development Server"),
                        new Server()
                                .url("http://localhost:8080/")
                                .description("Local Development Server (with trailing slash)")
                ))
                .info(new Info()
                        .title("ReplyTech Product Management API")
                        .description("A comprehensive REST API for managing products with filtering and search capabilities. " +
                                "This API provides endpoints for CRUD operations on products, including filtering by price range, " +
                                "category, and name search functionality.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("ReplyTech Development Team")
                                .email("dev@replytech.com")
                                .url("https://replytech.com"))
                        .license(new License()
                                .name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
