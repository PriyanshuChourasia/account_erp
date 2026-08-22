package com.codymitra.shared_service.modules.currency_minor_unit.mappers;

import com.codymitra.shared_service.modules.currency.entities.CurrencyEntity;
import com.codymitra.shared_service.modules.currency_minor_unit.dtos.CreateCurrencyMinorUnitDTO;
import com.codymitra.shared_service.modules.currency_minor_unit.dtos.CurrencyMinorUnitDTO;
import com.codymitra.shared_service.modules.currency_minor_unit.entities.CurrencyMinorUnitEntity;

public final class CurrencyMinorUnitMapper {

    public static CurrencyMinorUnitDTO minorUnitDTO(CurrencyMinorUnitEntity entity) {
        return new CurrencyMinorUnitDTO(
                entity.getId(),
                entity.getName(),
                entity.getSymbol(),
                entity.getValue(),
                entity.getCurrency() != null ? entity.getCurrency().getId() : null,
                entity.getActive()
        );
    }

    public static CurrencyMinorUnitEntity minorUnitEntity(CreateCurrencyMinorUnitDTO request, CurrencyEntity currency) {
        return applyRequest(new CurrencyMinorUnitEntity(), request, currency);
    }

    public static CurrencyMinorUnitEntity minorUnitEntity(CurrencyMinorUnitEntity entity, CreateCurrencyMinorUnitDTO request, CurrencyEntity currency) {
        return applyRequest(entity, request, currency);
    }

    private static CurrencyMinorUnitEntity applyRequest(CurrencyMinorUnitEntity entity, CreateCurrencyMinorUnitDTO request, CurrencyEntity currency) {
        entity.setName(request.name());
        entity.setSymbol(request.symbol());
        entity.setValue(request.value());
        entity.setCurrency(currency);
        if (request.active() != null) {
            entity.setActive(request.active());
        }
        return entity;
    }
}
