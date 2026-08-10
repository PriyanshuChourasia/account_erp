package com.codymitra.shared_service.modules.unit.dtos;

import com.codymitra.shared_service.modules.unit.enums.UnitTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UnitDTO(
        Long id,
        String name,
        String code,
        String alias,
        String description,
        UnitTypeEnum unitType,
        BaseUnitDTO primaryUnitId,
        BaseUnitDTO secondaryUnitId,
        BigDecimal conversionFactor,
        Integer decimalPlaces
) {
}
