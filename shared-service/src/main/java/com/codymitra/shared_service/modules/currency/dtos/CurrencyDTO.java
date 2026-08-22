package com.codymitra.shared_service.modules.currency.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CurrencyDTO(
        UUID id,
        String name,
        String currencySymbol,
        Integer decimalPlace,
        Boolean isSymbolSuffix,
        Boolean spaceBetweenAmountAndSymbol
) {
}
