package com.codymitra.shared_service.modules.currency.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateCurrencyDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Currency symbol is required")
        String currencySymbol,
        Integer decimalPlace,
        Boolean isSymbolSuffix,
        Boolean spaceBetweenAmountAndSymbol
) {
}
