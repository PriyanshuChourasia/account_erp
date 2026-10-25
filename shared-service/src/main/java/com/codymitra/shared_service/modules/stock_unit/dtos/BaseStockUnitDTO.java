package com.codymitra.shared_service.modules.stock_unit.dtos;

import java.util.UUID;

import com.codymitra.shared_service.enums.UnitTypeEnum;

import java.math.BigDecimal;

public record BaseStockUnitDTO(
        UUID id,
        String name,
        String code,
        String alias,
        String description,
        UnitTypeEnum unitType,
        BigDecimal conversionFactor,
        Integer decimalPlaces
) {}
