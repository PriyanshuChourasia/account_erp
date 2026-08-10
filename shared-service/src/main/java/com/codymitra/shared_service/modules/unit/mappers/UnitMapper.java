package com.codymitra.shared_service.modules.unit.mappers;

import com.codymitra.shared_service.modules.unit.dtos.BaseUnitDTO;
import com.codymitra.shared_service.modules.unit.dtos.CreateUnitRequestDTO;
import com.codymitra.shared_service.modules.unit.dtos.UnitDTO;
import com.codymitra.shared_service.modules.unit.entities.UnitEntity;
import com.codymitra.shared_service.modules.unit.enums.UnitTypeEnum;

public final class UnitMapper {

    public static UnitDTO unitDTO(UnitEntity unit, UnitEntity baseUnit1,UnitEntity baseUnit2) {

        return new UnitDTO(
                unit.getId(),
                unit.getName(),
                unit.getCode(),
                unit.getAlias(),
                unit.getDescription(),
                unit.getUnitType(),
                baseUnitDTO(baseUnit1),
                baseUnitDTO(baseUnit2),
                unit.getConversionFactor(),
                unit.getDecimalPlaces()
        );
    }

    public static UnitEntity unitEntity(CreateUnitRequestDTO unitRequest) {
        UnitEntity unit = new UnitEntity();
        unit.setName(unitRequest.name());
        String codeName = unitRequest.name().replace(" ", "_");
        unit.setCode(codeName.toUpperCase());
        unit.setAlias(unitRequest.alias());
        unit.setDescription(unitRequest.description());
        unit.setUnitType(unitRequest.unitType() != null ? UnitTypeEnum.valueOf(unitRequest.unitType()) : null);
        unit.setPrimaryUnitId(unitRequest.primaryUnitId());
        unit.setSecondaryUnitId(unitRequest.secondaryUnitId());
        unit.setConversionFactor(unitRequest.conversionFactor());
        unit.setDecimalPlaces(unitRequest.decimalPlaces());
        return unit;
    }

    public static BaseUnitDTO baseUnitDTO(UnitEntity unit){
        return new BaseUnitDTO(
                unit.getId(),
                unit.getName(),
                unit.getCode(),
                unit.getAlias(),
                unit.getDescription(),
                unit.getUnitType(),
                unit.getConversionFactor(),
                unit.getDecimalPlaces()
        );
    }
}
