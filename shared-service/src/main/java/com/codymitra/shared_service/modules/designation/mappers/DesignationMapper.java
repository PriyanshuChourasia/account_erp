package com.codymitra.shared_service.modules.designation.mappers;

import com.codymitra.shared_service.modules.designation.dtos.CreateDesignationRequest;
import com.codymitra.shared_service.modules.designation.dtos.DesignationDTO;
import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;

public final class DesignationMapper {

    public static DesignationDTO designationDTO(DesignationEntity entity) {
        return new DesignationDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getParentId(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static DesignationEntity designationEntity(CreateDesignationRequest request) {
        DesignationEntity entity = applyRequest(new DesignationEntity(), request);
        entity.setActive(request.active() != null ? request.active() : true);
        return entity;
    }

    public static DesignationEntity designationEntity(DesignationEntity entity, CreateDesignationRequest request) {
        applyRequest(entity, request);
        if (request.active() != null) {
            entity.setActive(request.active());
        }
        return entity;
    }

    public static String resolveCode(String code, String name) {
        if (code != null && !code.isBlank()) {
            return code.trim().toUpperCase();
        }
        return name.trim().replaceAll("[\\s-]+", "_").toUpperCase();
    }

    private static DesignationEntity applyRequest(DesignationEntity entity, CreateDesignationRequest request) {
        entity.setName(request.name().trim());
        entity.setCode(resolveCode(request.code(), request.name()));
        entity.setParentId(request.parentId());
        entity.setDescription(request.description());
        return entity;
    }
}
