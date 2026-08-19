package com.codymitra.shared_service.modules.stock_unit.dtos;

import java.util.UUID;
import com.codymitra.shared_service.modules.stock_unit.enums.StockUnitTypeEnum;
import com.codymitra.shared_service.modules.unique_quantity_code.dtos.UniqueQuantityCodeDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StockUnitDTO(
        UUID id,
        String name,
        String code,
        String alias,
        String description,
        StockUnitTypeEnum unitType,
        UniqueQuantityCodeDTO uqc,
        BaseStockUnitDTO primaryUnitId,
        BaseStockUnitDTO secondaryUnitId,
        BigDecimal conversionFactor,
        Integer decimalPlaces
) {
}
