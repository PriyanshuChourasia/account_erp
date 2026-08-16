package com.codymitra.shared_service.modules.uqc.mappers;

import com.codymitra.shared_service.modules.uqc.dtos.CreateUQCDTO;
import com.codymitra.shared_service.modules.uqc.dtos.UQCDTO;
import com.codymitra.shared_service.modules.uqc.entities.UQCEntity;

public final class UQCMapper {

    public static UQCDTO uqcDTO(UQCEntity uqc) {
        return new UQCDTO(
                uqc.getId(),
                uqc.getName(),
                uqc.getCode(),
                uqc.getAlias(),
                uqc.getDescription()
        );
    }

    public static UQCEntity uqcEntity(CreateUQCDTO request) {
        UQCEntity uqc = new UQCEntity();
        uqc.setName(request.name());
        uqc.setCode(request.code().toUpperCase());
        uqc.setAlias(request.alias());
        uqc.setDescription(request.description());
        return uqc;
    }
}
