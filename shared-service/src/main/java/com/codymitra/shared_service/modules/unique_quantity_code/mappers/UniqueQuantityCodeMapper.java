package com.codymitra.shared_service.modules.unique_quantity_code.mappers;

import com.codymitra.shared_service.modules.unique_quantity_code.dtos.CreateUniqueQuantityCodeDTO;
import com.codymitra.shared_service.modules.unique_quantity_code.dtos.UniqueQuantityCodeDTO;
import com.codymitra.shared_service.modules.unique_quantity_code.entities.UniqueQuantityCodeEntity;

public final class UniqueQuantityCodeMapper {

    public static UniqueQuantityCodeDTO uqcDTO(UniqueQuantityCodeEntity uqc) {
        return new UniqueQuantityCodeDTO(
                uqc.getId(),
                uqc.getName(),
                uqc.getCode(),
                uqc.getAlias(),
                uqc.getDescription()
        );
    }

    public static UniqueQuantityCodeEntity uqcEntity(CreateUniqueQuantityCodeDTO request) {
        UniqueQuantityCodeEntity uqc = new UniqueQuantityCodeEntity();
        uqc.setName(request.name());
        uqc.setCode(request.code().toUpperCase());
        uqc.setAlias(request.alias());
        uqc.setDescription(request.description());
        return uqc;
    }
}
