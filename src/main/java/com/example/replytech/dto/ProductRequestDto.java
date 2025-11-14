package com.example.replytech.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

/**
 * ProductRequestDto - Data Transfer Object for Product requests
 * 
 * Used for receiving product data from API clients. Includes validation
 * annotations to ensure data integrity before processing.
 * This DTO is mapped to Product entity in the service layer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Product Request DTO for creating/updating products")
public class ProductRequestDto {

    /**
     * Product name (required, 3-255 characters)
     */
    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 255, message = "Product name must be between 3 and 255 characters")
    @Schema(description = "Name of the product", example = "Laptop", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    /**
     * Product description (optional, max 1000 characters)
     */
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    @Schema(description = "Detailed description of the product", example = "High-performance laptop with 16GB RAM")
    private String description;

    /**
     * Product category (required, 2-100 characters)
     */
    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 100, message = "Category must be between 2 and 100 characters")
    @Schema(description = "Category of the product", example = "Electronics", requiredMode = Schema.RequiredMode.REQUIRED)
    private String category;

    /**
     * Product subcategory (optional, max 100 characters)
     */
    @Size(max = 100, message = "Subcategory cannot exceed 100 characters")
    @Schema(description = "Subcategory of the product", example = "Laptops")
    private String subcategory;

    /**
     * Seller name (required, 2-255 characters)
     */
    @NotBlank(message = "Seller name is required")
    @Size(min = 2, max = 255, message = "Seller name must be between 2 and 255 characters")
    @Schema(description = "Name of the seller", example = "Tech Store Inc.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sellerName;

    /**
     * Product price (required, must be > 0)
     */
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Schema(description = "Price of the product", example = "999.99", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal price;

    /**
     * Available quantity (required, must be >= 0)
     */
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Schema(description = "Available quantity in stock", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantity;
}
