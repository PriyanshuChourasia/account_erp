package com.codymitra.shared_service.modules.unit.dtos;

import com.codymitra.shared_service.modules.unit.enums.OperatorEnum;
import com.codymitra.shared_service.modules.unit.enums.UnitTypeEnum;

import java.math.BigDecimal;

public record BaseUnitDTO(
        Long id,
        String name,
        String code,
        String alias,
        String description,
        UnitTypeEnum unitType,
        BigDecimal conversionFactor,
        Integer decimalPlaces
) {}
