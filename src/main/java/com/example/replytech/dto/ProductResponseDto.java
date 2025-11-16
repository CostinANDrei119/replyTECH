package com.example.replytech.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;

/**
 * ProductResponseDto - Data Transfer Object for Product responses (Romanian fields)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Product Response DTO containing product details (Romanian fields)")
public class ProductResponseDto {

    @Schema(description = "Identificator unic al produsului", example = "1")
    private Long id;

    @Schema(description = "Nume produs", example = "Laptop")
    private String nume;

    @Schema(description = "Descriere produs", example = "Laptop performant cu 16GB RAM")
    private String descriere;

    @Schema(description = "Categorie produs", example = "Electronice")
    private String categorie;

    @Schema(description = "Subcategorie produs", example = "Laptopuri")
    private String subcategorie;

    @Schema(description = "Nume vanzator", example = "Magazin Tech SRL")
    private String numeVanzator;

    @Schema(description = "Pret produs", example = "999.99")
    private BigDecimal pret;

    @Schema(description = "Cantitate disponibila", example = "50")
    private Integer cantitate;
}
