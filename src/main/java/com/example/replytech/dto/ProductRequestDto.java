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
     * Nume - required
     */
    @NotBlank(message = "Nume este obligatoriu")
    @Size(min = 3, max = 255, message = "Numele trebuie sa fie intre 3 si 255 caractere")
    @Schema(description = "Nume produs", example = "Laptop", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nume;

    /**
     * Descriere - optional
     */
    @Size(max = 1000, message = "Descrierea nu poate depasi 1000 caractere")
    @Schema(description = "Descriere produs", example = "Laptop performant cu 16GB RAM")
    private String descriere;

    /**
     * Categorie - required
     */
    @NotBlank(message = "Categorie este obligatorie")
    @Size(min = 2, max = 100, message = "Categoria trebuie sa fie intre 2 si 100 caractere")
    @Schema(description = "Categorie produs", example = "Electronice", requiredMode = Schema.RequiredMode.REQUIRED)
    private String categorie;

    /**
     * Subcategorie - optional
     */
    @Size(max = 100, message = "Subcategoria nu poate depasi 100 caractere")
    @Schema(description = "Subcategorie produs", example = "Laptopuri")
    private String subcategorie;

    /**
     * Nume vanzator - required
     */
    @NotBlank(message = "Nume vanzator este obligatoriu")
    @Size(min = 2, max = 255, message = "Numele vanzatorului trebuie sa fie intre 2 si 255 caractere")
    @Schema(description = "Nume vanzator", example = "Magazin Tech SRL", requiredMode = Schema.RequiredMode.REQUIRED)
    private String numeVanzator;

    /**
     * Pret - required
     */
    @NotNull(message = "Pretul este obligatoriu")
    @DecimalMin(value = "0.01", message = "Pretul trebuie sa fie mai mare decat 0")
    @Schema(description = "Pret produs", example = "999.99", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal pret;

    /**
     * Cantitate - required
     */
    @NotNull(message = "Cantitatea este obligatorie")
    @Min(value = 0, message = "Cantitatea nu poate fi negativa")
    @Schema(description = "Cantitate disponibila", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer cantitate;
}
