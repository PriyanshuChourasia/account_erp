package com.codymitra.shared_service.modules.currency_numbering_system.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateCurrencyNumberingSystemDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Code is required")
        String code,
        String description,
        Boolean active
) {
}
