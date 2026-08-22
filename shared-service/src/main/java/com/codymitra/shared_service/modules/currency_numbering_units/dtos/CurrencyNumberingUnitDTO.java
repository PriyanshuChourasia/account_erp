package com.codymitra.shared_service.modules.currency_numbering_units.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CurrencyNumberingUnitDTO(
        UUID id,
        String name,
        String symbol,
        BigDecimal value,
        Integer sequence,
        UUID numberingSystemId,
        Boolean active
) {
}
