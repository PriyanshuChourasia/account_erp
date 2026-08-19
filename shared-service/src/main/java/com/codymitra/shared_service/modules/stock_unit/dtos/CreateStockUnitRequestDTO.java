package com.codymitra.shared_service.modules.stock_unit.dtos;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateStockUnitRequestDTO(
        @NotBlank(message = "Name is required")
        String name,
        String alias,
        String description,
        String code,
        String unitType,
        UUID uqcId,
        UUID primaryUnitId,
        UUID secondaryUnitId,
        BigDecimal conversionFactor,
        Integer decimalPlaces
) {}
