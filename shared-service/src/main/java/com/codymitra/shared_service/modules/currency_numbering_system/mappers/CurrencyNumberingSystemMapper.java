package com.codymitra.shared_service.modules.currency_numbering_system.mappers;

import com.codymitra.shared_service.modules.currency_numbering_system.dtos.CreateCurrencyNumberingSystemDTO;
import com.codymitra.shared_service.modules.currency_numbering_system.dtos.CurrencyNumberingSystemDTO;
import com.codymitra.shared_service.modules.currency_numbering_system.entities.CurrencyNumberingSystemEntity;

public final class CurrencyNumberingSystemMapper {

    public static CurrencyNumberingSystemDTO systemDTO(CurrencyNumberingSystemEntity entity) {
        return new CurrencyNumberingSystemDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static CurrencyNumberingSystemEntity systemEntity(CreateCurrencyNumberingSystemDTO request) {
        return applyRequest(new CurrencyNumberingSystemEntity(), request);
    }

    public static CurrencyNumberingSystemEntity systemEntity(CurrencyNumberingSystemEntity entity, CreateCurrencyNumberingSystemDTO request) {
        return applyRequest(entity, request);
    }

    private static CurrencyNumberingSystemEntity applyRequest(CurrencyNumberingSystemEntity entity, CreateCurrencyNumberingSystemDTO request) {
        entity.setName(request.name());
        entity.setCode(request.code().toUpperCase());
        entity.setDescription(request.description());
        if (request.active() != null) {
            entity.setActive(request.active());
        }
        return entity;
    }
}
