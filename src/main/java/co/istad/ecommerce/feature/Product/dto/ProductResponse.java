package co.istad.ecommerce.feature.Product.dto;

import co.istad.ecommerce.feature.Category.dto.CategorySnippetResponse;

import java.math.BigDecimal;

public record ProductResponse(
        String code,
        String slud,
        String name,
        String thumbnail,
        BigDecimal unitPrice,
        Integer qty,
        Boolean isAvailable,
        Boolean isDeleted,
        CategorySnippetResponse category
) {
}
