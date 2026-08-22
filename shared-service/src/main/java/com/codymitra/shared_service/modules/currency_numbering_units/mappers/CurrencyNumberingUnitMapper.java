package com.codymitra.shared_service.modules.currency_numbering_units.mappers;

import com.codymitra.shared_service.modules.currency_numbering_system.entities.CurrencyNumberingSystemEntity;
import com.codymitra.shared_service.modules.currency_numbering_units.dtos.CreateCurrencyNumberingUnitDTO;
import com.codymitra.shared_service.modules.currency_numbering_units.dtos.CurrencyNumberingUnitDTO;
import com.codymitra.shared_service.modules.currency_numbering_units.entities.CurrencyNumberingUnitEntity;

public final class CurrencyNumberingUnitMapper {

    public static CurrencyNumberingUnitDTO unitDTO(CurrencyNumberingUnitEntity entity) {
        return new CurrencyNumberingUnitDTO(
                entity.getId(),
                entity.getName(),
                entity.getSymbol(),
                entity.getValue(),
                entity.getSequence(),
                entity.getNumberingSystem() != null ? entity.getNumberingSystem().getId() : null,
                entity.getActive()
        );
    }

    public static CurrencyNumberingUnitEntity unitEntity(CreateCurrencyNumberingUnitDTO request, CurrencyNumberingSystemEntity numberingSystem, Integer sequence) {
        return applyRequest(new CurrencyNumberingUnitEntity(), request, numberingSystem, sequence);
    }

    public static CurrencyNumberingUnitEntity unitEntity(CurrencyNumberingUnitEntity entity, CreateCurrencyNumberingUnitDTO request, CurrencyNumberingSystemEntity numberingSystem) {
        return applyRequest(entity, request, numberingSystem, request.sequence() != null ? request.sequence() : entity.getSequence());
    }

    private static CurrencyNumberingUnitEntity applyRequest(CurrencyNumberingUnitEntity entity, CreateCurrencyNumberingUnitDTO request, CurrencyNumberingSystemEntity numberingSystem, Integer sequence) {
        entity.setName(request.name());
        entity.setSymbol(request.symbol());
        entity.setValue(request.value());
        entity.setSequence(sequence);
        entity.setNumberingSystem(numberingSystem);
        if (request.active() != null) {
            entity.setActive(request.active());
        }
        return entity;
    }
}
