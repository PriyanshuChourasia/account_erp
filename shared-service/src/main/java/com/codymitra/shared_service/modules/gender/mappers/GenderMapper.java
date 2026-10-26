package com.codymitra.shared_service.modules.gender.mappers;

import com.codymitra.shared_service.modules.gender.dtos.CreateGenderRequest;
import com.codymitra.shared_service.modules.gender.dtos.GenderDTO;
import com.codymitra.shared_service.modules.gender.entities.GenderEntity;

public final class GenderMapper {

    public static GenderDTO genderDTO(GenderEntity entity) {
        return new GenderDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getStatus()
        );
    }

    public static GenderEntity genderEntity(CreateGenderRequest request) {
        GenderEntity entity = applyRequest(new GenderEntity(), request);
        entity.setStatus(true);
        return entity;
    }

    public static GenderEntity genderEntity(GenderEntity entity, CreateGenderRequest request) {
        return applyRequest(entity, request);
    }

    public static String resolveCode(String code) {
        return code == null || code.isBlank() ? null : code.trim();
    }

    private static GenderEntity applyRequest(GenderEntity entity, CreateGenderRequest request) {
        entity.setName(request.name().trim());
        entity.setCode(resolveCode(request.code()));
        entity.setDescription(request.description());
        return entity;
    }
}
