package com.codymitra.shared_service.modules.currency_minor_unit.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateCurrencyMinorUnitDTO(
        @NotBlank(message = "Name is required")
        String name,
        String symbol,
        BigDecimal value,
        @NotNull(message = "Currency is required")
        UUID currencyId,
        Boolean active
) {
}
