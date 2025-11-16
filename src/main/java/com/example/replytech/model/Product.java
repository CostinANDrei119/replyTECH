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
     * Nume (product name) - required
     */
    @NotBlank(message = "Nume este obligatoriu")
    @Size(min = 3, max = 255, message = "Numele trebuie sa fie intre 3 si 255 caractere")
    @Column(name = "nume", nullable = false)
    private String nume;

    /**
     * Descriere (description) - optional
     */
    @Size(max = 1000, message = "Descrierea nu poate depasi 1000 caractere")
    @Column(name = "descriere", columnDefinition = "TEXT")
    private String descriere;

    /**
     * Categorie - required
     */
    @NotBlank(message = "Categorie este obligatorie")
    @Size(min = 2, max = 100, message = "Categoria trebuie sa fie intre 2 si 100 caractere")
    @Column(name = "categorie", nullable = false)
    private String categorie;

    /**
     * Subcategorie - optional
     */
    @Size(max = 100, message = "Subcategoria nu poate depasi 100 caractere")
    @Column(name = "subcategorie")
    private String subcategorie;

    /**
     * Nume vanzator (seller name) - required
     */
    @NotBlank(message = "Nume vanzator este obligatoriu")
    @Size(min = 2, max = 255, message = "Numele vanzatorului trebuie sa fie intre 2 si 255 caractere")
    @Column(name = "nume_vanzator", nullable = false)
    private String numeVanzator;

    /**
     * Pret (price) - required
     */
    @NotNull(message = "Pretul este obligatoriu")
    @DecimalMin(value = "0.01", message = "Pretul trebuie sa fie mai mare decat 0")
    @Column(name = "pret", nullable = false, precision = 10, scale = 2)
    private BigDecimal pret;

    /**
     * Cantitate (quantity) - required
     */
    @NotNull(message = "Cantitatea este obligatorie")
    @Min(value = 0, message = "Cantitatea nu poate fi negativa")
    @Column(name = "cantitate", nullable = false)
    private Integer cantitate;
}
