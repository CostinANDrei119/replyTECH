package com.example.replytech.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ProductResponseDto - Data Transfer Object for Product responses
 * 
 * Used for sending product data to API clients. Includes additional
 * metadata like creation/update timestamps.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Product Response DTO containing product details")
public class ProductResponseDto {

    /**
     * Unique product identifier
     */
    @Schema(description = "Unique identifier of the product", example = "1")
    private Long id;

    /**
     * Product name
     */
    @Schema(description = "Name of the product", example = "Laptop")
    private String name;

    /**
     * Product description
     */
    @Schema(description = "Detailed description of the product", example = "High-performance laptop with 16GB RAM")
    private String description;

    /**
     * Product category
     */
    @Schema(description = "Category of the product", example = "Electronics")
    private String category;

    /**
     * Product subcategory
     */
    @Schema(description = "Subcategory of the product", example = "Laptops")
    private String subcategory;

    /**
     * Seller name
     */
    @Schema(description = "Name of the seller", example = "Tech Store Inc.")
    private String sellerName;

    /**
     * Product price
     */
    @Schema(description = "Price of the product", example = "999.99")
    private BigDecimal price;

    /**
     * Available quantity
     */
    @Schema(description = "Available quantity in stock", example = "50")
    private Integer quantity;

    /**
     * Creation timestamp
     */
    @Schema(description = "Timestamp when product was created")
    private LocalDateTime createdAt;

    /**
     * Last update timestamp
     */
    @Schema(description = "Timestamp when product was last updated")
    private LocalDateTime updatedAt;
}
