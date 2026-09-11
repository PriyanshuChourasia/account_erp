package com.codymitra.shared_service.modules.bank_type.mappers;

import com.codymitra.shared_service.modules.bank_type.dtos.BankTypeDTO;
import com.codymitra.shared_service.modules.bank_type.dtos.CreateBankTypeRequest;
import com.codymitra.shared_service.modules.bank_type.entities.BankTypeEntity;

public final class BankTypeMapper {

    public static BankTypeDTO bankTypeDTO(BankTypeEntity entity) {
        return new BankTypeDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static BankTypeEntity bankTypeEntity(CreateBankTypeRequest request) {
        BankTypeEntity entity = new BankTypeEntity();
        entity.setName(request.name());
        entity.setCode(request.code());
        entity.setDescription(request.description());
        entity.setActive(true);
        return entity;
    }
}