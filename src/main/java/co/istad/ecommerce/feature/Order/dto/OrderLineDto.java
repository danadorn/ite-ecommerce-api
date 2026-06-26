package co.istad.ecommerce.feature.Order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.aspectj.bridge.Message;

import java.math.BigDecimal;

public record OrderLineDto(
        @NotBlank(message = "Code is required")
        String code,
        @Positive
        @NotNull(message = "Quantity is required")
        Integer qty,
        @NotBlank(message = "Unit price is required")
        BigDecimal unitPrice
) {
}
