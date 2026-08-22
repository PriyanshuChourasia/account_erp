package com.codymitra.shared_service.modules.currency_numbering_system.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CurrencyNumberingSystemDTO(
        UUID id,
        String name,
        String description,
        String groupingPattern,
        Boolean active
) {
}
