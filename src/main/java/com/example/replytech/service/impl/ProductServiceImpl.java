package com.example.replytech.service.impl;

import com.example.replytech.dto.ProductRequestDto;
import com.example.replytech.dto.ProductResponseDto;
import com.example.replytech.model.Product;
import com.example.replytech.repository.ProductRepository;
import com.example.replytech.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ProductServiceImpl - Implementation of ProductService
 * 
 * Contains business logic for product operations. This layer handles
 * validation, data transformation (DTO to Entity), and orchestration
 * of repository calls.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * Converts a Product entity to ProductResponseDto
     * 
     * @param product the product entity
     * @return the response DTO
     */
    private ProductResponseDto convertToResponseDto(Product product) {
        log.debug("Converting Product entity to ProductResponseDto for product ID: {}", product.getId());
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .subcategory(product.getSubcategory())
                .sellerName(product.getSellerName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    /**
     * Converts a ProductRequestDto to Product entity
     * 
     * @param requestDto the request DTO
     * @return the product entity
     */
    private Product convertToEntity(ProductRequestDto requestDto) {
        log.debug("Converting ProductRequestDto to Product entity");
        return Product.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .category(requestDto.getCategory())
                .subcategory(requestDto.getSubcategory())
                .sellerName(requestDto.getSellerName())
                .price(requestDto.getPrice())
                .quantity(requestDto.getQuantity())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProducts() {
        log.info("Fetching all products from database");
        return productRepository.findAll()
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto getProductById(Long id) {
        log.info("Fetching product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new RuntimeException("Product not found with ID: " + id);
                });
        return convertToResponseDto(product);
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        log.info("Creating new product: {}", requestDto.getName());
        Product product = convertToEntity(requestDto);
        Product savedProduct = productRepository.save(product);
        log.info("Product created successfully with ID: {}", savedProduct.getId());
        return convertToResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        log.info("Updating product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new RuntimeException("Product not found with ID: " + id);
                });

        // Update product fields from request DTO
        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        product.setCategory(requestDto.getCategory());
        product.setSubcategory(requestDto.getSubcategory());
        product.setSellerName(requestDto.getSellerName());
        product.setPrice(requestDto.getPrice());
        product.setQuantity(requestDto.getQuantity());

        Product updatedProduct = productRepository.save(product);
        log.info("Product updated successfully with ID: {}", updatedProduct.getId());
        return convertToResponseDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);
        if (!productRepository.existsById(id)) {
            log.error("Product not found with ID: {}", id);
            throw new RuntimeException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
        log.info("Product deleted successfully with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> searchByName(String name) {
        log.info("Searching products by name: {}", name);
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findByCategory(String category) {
        log.info("Finding products by category: {}", category);
        return productRepository.findByCategory(category)
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        log.info("Finding products by price range: {} - {}", minPrice, maxPrice);
        return productRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> searchByNameAndPrice(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        log.info("Searching products by name: {} and price range: {} - {}", name, minPrice, maxPrice);
        return productRepository.findByNameAndPriceRange(name, minPrice, maxPrice)
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getInStockProducts() {
        log.info("Fetching in-stock products");
        return productRepository.findInStockProducts()
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
}
