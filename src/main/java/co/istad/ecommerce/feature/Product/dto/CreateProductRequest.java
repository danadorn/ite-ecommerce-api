package co.istad.ecommerce.feature.Product.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record   CreateProductRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 255)
        String name,
        @Size(max = 500)
        String description,
        @Size(max = 255)
        String thumbnail,
        @NotNull(message = "Unit price id required")
        @Min(0)
        BigDecimal unitPrice,
        @Min(0)
        @NotNull(message = "QTY is required")
        @Positive
        Integer qty,
        @NotNull
        @Positive
        Integer categoryId
) {
}
