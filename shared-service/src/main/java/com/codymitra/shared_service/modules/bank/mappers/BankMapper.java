package com.codymitra.shared_service.modules.bank.mappers;

import com.codymitra.shared_service.modules.bank.dtos.BankDTO;
import com.codymitra.shared_service.modules.bank.dtos.CreateBankRequest;
import com.codymitra.shared_service.modules.bank.entities.BankEntity;

public final class BankMapper {

    public static BankDTO bankDTO(BankEntity entity) {
        return new BankDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static BankEntity bankEntity(CreateBankRequest request) {
        BankEntity entity = new BankEntity();
        entity.setName(request.name());
        entity.setCode(request.code());
        entity.setDescription(request.description());
        entity.setActive(true);
        return entity;
    }
}