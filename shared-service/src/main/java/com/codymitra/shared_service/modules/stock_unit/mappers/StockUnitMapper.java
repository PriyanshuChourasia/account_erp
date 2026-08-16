package com.codymitra.shared_service.modules.stock_unit.mappers;

import com.codymitra.shared_service.modules.stock_unit.dtos.BaseStockUnitDTO;
import com.codymitra.shared_service.modules.stock_unit.dtos.CreateStockUnitRequestDTO;
import com.codymitra.shared_service.modules.stock_unit.dtos.StockUnitDTO;
import com.codymitra.shared_service.modules.stock_unit.entities.StockUnitEntity;
import com.codymitra.shared_service.modules.stock_unit.enums.StockUnitTypeEnum;
import com.codymitra.shared_service.modules.uqc.entities.UQCEntity;
import com.codymitra.shared_service.modules.uqc.mappers.UQCMapper;

public final class StockUnitMapper {

    public static StockUnitDTO stockUnitDTO(StockUnitEntity unit, StockUnitEntity baseUnit1,StockUnitEntity baseUnit2) {

        return new StockUnitDTO(
                unit.getId(),
                unit.getName(),
                unit.getCode(),
                unit.getAlias(),
                unit.getDescription(),
                unit.getUnitType(),
                unit.getUqc() != null ? UQCMapper.uqcDTO(unit.getUqc()) : null,
                baseStockUnitDTO(baseUnit1),
                baseStockUnitDTO(baseUnit2),
                unit.getConversionFactor(),
                unit.getDecimalPlaces()
        );
    }

    public static StockUnitEntity stockUnitEntity(CreateStockUnitRequestDTO unitRequest, UQCEntity uqc) {
        StockUnitEntity unit = new StockUnitEntity();
        return applyRequest(unit, unitRequest, uqc);
    }

    public static StockUnitEntity stockUnitEntity(StockUnitEntity unit, CreateStockUnitRequestDTO unitRequest, UQCEntity uqc) {
        return applyRequest(unit, unitRequest, uqc);
    }

    private static StockUnitEntity applyRequest(StockUnitEntity unit, CreateStockUnitRequestDTO unitRequest, UQCEntity uqc) {
        unit.setName(unitRequest.name());
        String codeName = unitRequest.name().replace(" ", "_");
        unit.setCode(codeName.toUpperCase());
        unit.setAlias(unitRequest.alias());
        unit.setDescription(unitRequest.description());
        unit.setUnitType(unitRequest.unitType() != null ? StockUnitTypeEnum.valueOf(unitRequest.unitType()) : null);
        unit.setUqc(uqc);
        unit.setPrimaryUnitId(unitRequest.primaryUnitId());
        unit.setSecondaryUnitId(unitRequest.secondaryUnitId());
        unit.setConversionFactor(unitRequest.conversionFactor());
        unit.setDecimalPlaces(unitRequest.decimalPlaces());
        return unit;
    }

    public static BaseStockUnitDTO baseStockUnitDTO(StockUnitEntity unit){
        return new BaseStockUnitDTO(
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
