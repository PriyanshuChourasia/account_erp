package com.codymitra.organization_service.modules.legal_entity.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateLegalEntityDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Code is required")
        String code,
        String description,
        @NotNull(message = "Country Id is required")
        Long countryId
) { }
