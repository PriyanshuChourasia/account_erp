package com.codymitra.shared_service.modules.currency_minor_unit.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CurrencyMinorUnitDTO(
        UUID id,
        String name,
        String symbol,
        BigDecimal value,
        UUID currencyId,
        Boolean active
) {
}
