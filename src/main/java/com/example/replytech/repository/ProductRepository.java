package com.example.replytech.repository;

import com.example.replytech.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

/**
 * ProductRepository - Data access layer for Product entity
 * 
 * Extends JpaRepository to provide CRUD operations and custom query methods
 * for searching and filtering products based on various criteria.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    

    /**
     * Find all products in a specific category
     * 
     * @param category the category to search for
     * @return list of products in the specified category
     */
    List<Product> findByCategorie(String categorie);

    /**
     * Find all products by seller name
     * 
     * @param sellerName the seller to search for
     * @return list of products from the specified seller
     */
    List<Product> findByNumeVanzator(String numeVanzator);

    /**
     * Find products within a price range (inclusive)
     * 
     * @param minPrice minimum price
     * @param maxPrice maximum price
     * @return list of products within the price range
     */
    @Query("SELECT p FROM Product p WHERE p.pret BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPretBetween(@Param("minPrice") BigDecimal minPrice, 
                                     @Param("maxPrice") BigDecimal maxPrice);

    /**
     * Find products by name and price range
     * 
     * @param name product name (partial match)
     * @param minPrice minimum price
     * @param maxPrice maximum price
     * @return list of products matching all criteria
     */
        @Query("SELECT p FROM Product p WHERE LOWER(p.nume) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND p.pret BETWEEN :minPrice AND :maxPrice")
        List<Product> findByNumeAndPretRange(@Param("name") String name, 
                              @Param("minPrice") BigDecimal minPrice,
                              @Param("maxPrice") BigDecimal maxPrice);

    /**
     * Find products in stock (quantity > 0)
     * 
     * @return list of products that have quantity greater than 0
     */
    @Query("SELECT p FROM Product p WHERE p.cantitate > 0")
    List<Product> findInStockProducts();

    List<Product> findByNumeContainingIgnoreCase(String nume);
}
