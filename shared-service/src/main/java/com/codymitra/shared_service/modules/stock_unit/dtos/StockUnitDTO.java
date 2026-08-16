package com.codymitra.shared_service.modules.stock_unit.dtos;

import com.codymitra.shared_service.modules.stock_unit.enums.StockUnitTypeEnum;
import com.codymitra.shared_service.modules.uqc.dtos.UQCDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StockUnitDTO(
        Long id,
        String name,
        String code,
        String alias,
        String description,
        StockUnitTypeEnum unitType,
        UQCDTO uqc,
        BaseStockUnitDTO primaryUnitId,
        BaseStockUnitDTO secondaryUnitId,
        BigDecimal conversionFactor,
        Integer decimalPlaces
) {
}
