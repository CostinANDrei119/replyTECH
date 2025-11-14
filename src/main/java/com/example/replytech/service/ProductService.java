package com.example.replytech.service;

import com.example.replytech.dto.ProductRequestDto;
import com.example.replytech.dto.ProductResponseDto;
import java.math.BigDecimal;
import java.util.List;

/**
 * ProductService - Interface for product business logic
 * 
 * Defines contract for product-related operations including CRUD,
 * searching, and filtering functionality.
 */
public interface ProductService {

    /**
     * Retrieve all products
     * 
     * @return list of all products with response data
     */
    List<ProductResponseDto> getAllProducts();

    /**
     * Retrieve a product by ID
     * 
     * @param id the product ID
     * @return the product response data if found
     * @throws RuntimeException if product not found
     */
    ProductResponseDto getProductById(Long id);

    /**
     * Create a new product
     * 
     * @param requestDto the product request data
     * @return the created product response data
     */
    ProductResponseDto createProduct(ProductRequestDto requestDto);

    /**
     * Update an existing product
     * 
     * @param id the product ID
     * @param requestDto the updated product data
     * @return the updated product response data
     * @throws RuntimeException if product not found
     */
    ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto);

    /**
     * Delete a product
     * 
     * @param id the product ID
     * @throws RuntimeException if product not found
     */
    void deleteProduct(Long id);

    /**
     * Search products by name
     * 
     * @param name search term
     * @return list of matching products
     */
    List<ProductResponseDto> searchByName(String name);

    /**
     * Find products by category
     * 
     * @param category the category name
     * @return list of products in the category
     */
    List<ProductResponseDto> findByCategory(String category);

    /**
     * Find products within price range
     * 
     * @param minPrice minimum price
     * @param maxPrice maximum price
     * @return list of products within range
     */
    List<ProductResponseDto> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Search products by name and price range
     * 
     * @param name search term
     * @param minPrice minimum price
     * @param maxPrice maximum price
     * @return list of products matching all criteria
     */
    List<ProductResponseDto> searchByNameAndPrice(String name, BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Get products that are in stock
     * 
     * @return list of products with quantity > 0
     */
    List<ProductResponseDto> getInStockProducts();
}
