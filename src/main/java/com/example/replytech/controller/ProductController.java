package com.example.replytech.controller;

import com.example.replytech.dto.ProductRequestDto;
import com.example.replytech.dto.ProductResponseDto;
import com.example.replytech.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * ProductController - REST API endpoints for product management
 * 
 * Provides endpoints for CRUD operations and searching/filtering products.
 * All endpoints are documented with Swagger/OpenAPI annotations.
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Products", description = "API endpoints for managing products")
public class ProductController {

    private final ProductService productService;

    /**
     * Get all products
     * 
     * @return list of all products with HTTP 200
     */
    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieve a list of all available products")
    @ApiResponse(responseCode = "200", description = "Products retrieved successfully",
            content = @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        log.info("GET /api/products - Fetching all products");
        List<ProductResponseDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Get product by ID
     * 
     * @param id product ID
     * @return product details with HTTP 200, or 404 if not found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieve a specific product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found",
                    content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductResponseDto> getProductById(
            @PathVariable
            @Parameter(description = "Product ID", example = "1")
            Long id) {
        log.info("GET /api/products/{} - Fetching product by ID", id);
        ProductResponseDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * Create new product
     * 
     * @param requestDto product data
     * @return created product with HTTP 201
     */
    @PostMapping
    @Operation(summary = "Create new product", description = "Create a new product entry in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully",
                    content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<ProductResponseDto> createProduct(
            @Valid @RequestBody ProductRequestDto requestDto) {
        log.info("POST /api/products - Creating new product: {}", requestDto.getName());
        ProductResponseDto createdProduct = productService.createProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Update existing product
     * 
     * @param id product ID
     * @param requestDto updated product data
     * @return updated product with HTTP 200
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update product", description = "Update an existing product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully",
                    content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable
            @Parameter(description = "Product ID", example = "1")
            Long id,
            @Valid @RequestBody ProductRequestDto requestDto) {
        log.info("PUT /api/products/{} - Updating product", id);
        ProductResponseDto updatedProduct = productService.updateProduct(id, requestDto);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Delete product
     * 
     * @param id product ID
     * @return HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product", description = "Delete a product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Void> deleteProduct(
            @PathVariable
            @Parameter(description = "Product ID", example = "1")
            Long id) {
        log.info("DELETE /api/products/{} - Deleting product", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Search products by name
     * 
     * @param name search term
     * @return list of matching products with HTTP 200
     */
    @GetMapping("/search/name")
    @Operation(summary = "Search products by name", description = "Search for products by name (partial match)")
    @ApiResponse(responseCode = "200", description = "Search completed successfully",
            content = @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    public ResponseEntity<List<ProductResponseDto>> searchByName(
            @RequestParam
            @Parameter(description = "Product name to search for", example = "Laptop")
            String name) {
        log.info("GET /api/products/search/name?name={} - Searching products by name", name);
        List<ProductResponseDto> products = productService.searchByName(name);
        return ResponseEntity.ok(products);
    }

    /**
     * Find products by category
     * 
     * @param category category name
     * @return list of products in category with HTTP 200
     */
    @GetMapping("/category/{category}")
    @Operation(summary = "Find products by category", description = "Get all products in a specific category")
    @ApiResponse(responseCode = "200", description = "Products found",
            content = @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    public ResponseEntity<List<ProductResponseDto>> findByCategory(
            @PathVariable
            @Parameter(description = "Category name", example = "Electronics")
            String category) {
        log.info("GET /api/products/category/{} - Finding products by category", category);
        List<ProductResponseDto> products = productService.findByCategory(category);
        return ResponseEntity.ok(products);
    }

    /**
     * Find products by price range
     * 
     * @param minPrice minimum price
     * @param maxPrice maximum price
     * @return list of products within price range with HTTP 200
     */
    @GetMapping("/price-range")
    @Operation(summary = "Find products by price range", description = "Get products within a specific price range")
    @ApiResponse(responseCode = "200", description = "Products found",
            content = @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    public ResponseEntity<List<ProductResponseDto>> findByPriceRange(
            @RequestParam
            @Parameter(description = "Minimum price", example = "100.00")
            BigDecimal minPrice,
            @RequestParam
            @Parameter(description = "Maximum price", example = "1000.00")
            BigDecimal maxPrice) {
        log.info("GET /api/products/price-range?minPrice={}&maxPrice={} - Finding products by price range", minPrice, maxPrice);
        List<ProductResponseDto> products = productService.findByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    /**
     * Search products by name and price range
     * 
     * @param name product name
     * @param minPrice minimum price
     * @param maxPrice maximum price
     * @return list of matching products with HTTP 200
     */
    @GetMapping("/search")
    @Operation(summary = "Search products by name and price", description = "Search for products with name and price range filters")
    @ApiResponse(responseCode = "200", description = "Search completed successfully",
            content = @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    public ResponseEntity<List<ProductResponseDto>> searchByNameAndPrice(
            @RequestParam
            @Parameter(description = "Product name to search for", example = "Laptop")
            String name,
            @RequestParam
            @Parameter(description = "Minimum price", example = "100.00")
            BigDecimal minPrice,
            @RequestParam
            @Parameter(description = "Maximum price", example = "1000.00")
            BigDecimal maxPrice) {
        log.info("GET /api/products/search?name={}&minPrice={}&maxPrice={} - Searching products", name, minPrice, maxPrice);
        List<ProductResponseDto> products = productService.searchByNameAndPrice(name, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    /**
     * Get in-stock products
     * 
     * @return list of products with quantity > 0 with HTTP 200
     */
    @GetMapping("/in-stock")
    @Operation(summary = "Get in-stock products", description = "Retrieve all products that are currently in stock")
    @ApiResponse(responseCode = "200", description = "In-stock products retrieved successfully",
            content = @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    public ResponseEntity<List<ProductResponseDto>> getInStockProducts() {
        log.info("GET /api/products/in-stock - Fetching in-stock products");
        List<ProductResponseDto> products = productService.getInStockProducts();
        return ResponseEntity.ok(products);
    }
}
