package com.example.replytech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

/**
 * Product Entity - Represents a product in the database
 * Maps to the 'products' table
 * 
 * This entity contains all product details including name, description,
 * category information, pricing, and inventory management.
 */
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    /**
     * Unique identifier for the product (Auto-generated)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Product name (required, max 255 characters)
     */
    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 255, message = "Product name must be between 3 and 255 characters")
    @Column(nullable = false)
    private String name;

    /**
     * Product description (optional, max 1000 characters)
     */
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Product category (required, e.g., "Electronics", "Clothing")
     */
    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 100, message = "Category must be between 2 and 100 characters")
    @Column(nullable = false)
    private String category;

    /**
     * Product subcategory (optional, e.g., "Mobile Phones", "T-Shirts")
     */
    @Size(max = 100, message = "Subcategory cannot exceed 100 characters")
    private String subcategory;

    /**
     * Seller name (required)
     */
    @NotBlank(message = "Seller name is required")
    @Size(min = 2, max = 255, message = "Seller name must be between 2 and 255 characters")
    @Column(nullable = false)
    private String sellerName;

    /**
     * Product price (required, must be positive)
     */
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * Available quantity in stock (required, must be non-negative)
     */
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(nullable = false)
    private Integer quantity;

    /**
     * Timestamp when product was created (auto-set)
     */
    @Column(nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    /**
     * Timestamp when product was last updated (auto-updated)
     */
    @Column(nullable = false)
    private java.time.LocalDateTime updatedAt;

    /**
     * Pre-persist hook to set creation timestamp
     */
    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
        updatedAt = java.time.LocalDateTime.now();
    }

    /**
     * Pre-update hook to update modification timestamp
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = java.time.LocalDateTime.now();
    }
}
