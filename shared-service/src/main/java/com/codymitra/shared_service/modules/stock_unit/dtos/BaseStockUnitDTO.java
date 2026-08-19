package com.codymitra.shared_service.modules.stock_unit.dtos;

import java.util.UUID;
import com.codymitra.shared_service.modules.stock_unit.enums.StockUnitTypeEnum;

import java.math.BigDecimal;

public record BaseStockUnitDTO(
        UUID id,
        String name,
        String code,
        String alias,
        String description,
        StockUnitTypeEnum unitType,
        BigDecimal conversionFactor,
        Integer decimalPlaces
) {}
