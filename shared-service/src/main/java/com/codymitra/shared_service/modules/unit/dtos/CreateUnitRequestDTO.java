package com.codymitra.shared_service.modules.unit.dtos;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CreateUnitRequestDTO(
        @NotBlank(message = "Name is required")
        String name,
        String alias,
        String description,
        String code,
        String unitType,
        Long primaryUnitId,
        Long secondaryUnitId,
        BigDecimal conversionFactor,
        Integer decimalPlaces
) {}
