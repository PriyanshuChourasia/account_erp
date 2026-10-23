package com.codymitra.shared_service.modules.designation.mappers;

import com.codymitra.shared_service.modules.designation.dtos.CreateDesignationRequest;
import com.codymitra.shared_service.modules.designation.dtos.DesignationDTO;
import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;

import java.util.UUID;

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
        entity.setActive(true);
        return entity;
    }

    public static DesignationEntity designationEntity(DesignationEntity entity, CreateDesignationRequest request) {
        applyRequest(entity, request);
        entity.setActive(true);
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
        entity.setDescription(request.description());
        entity.setParentId(parentId(request));
        return entity;
    }

    /** parentId arrives as a String; blank means "no parent" (top level). */
    public static UUID parentId(CreateDesignationRequest request) {
        if (request.parentId() == null || request.parentId().isBlank()) {
            return null;
        }
        try {
            return UUID.fromString(request.parentId().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid parent id: " + request.parentId());
        }
    }
}
