package com.codymitra.shared_service.modules.user_type.mappers;

import com.codymitra.shared_service.modules.user_type.dtos.CreateUserTypeRequest;
import com.codymitra.shared_service.modules.user_type.dtos.UserTypeDTO;
import com.codymitra.shared_service.modules.user_type.entities.UserTypeEntity;

public final class UserTypeMapper {

    public static UserTypeDTO userTypeDTO(UserTypeEntity entity) {
        return new UserTypeDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static UserTypeEntity userTypeEntity(CreateUserTypeRequest request) {
        UserTypeEntity entity = applyRequest(new UserTypeEntity(), request);
        entity.setActive(true);
        return entity;
    }

    public static UserTypeEntity userTypeEntity(UserTypeEntity entity, CreateUserTypeRequest request) {
        return applyRequest(entity, request);
    }

    public static String resolveCode(String code, String name) {
        if (code != null && !code.isBlank()) {
            return code.trim().toUpperCase();
        }
        return name.trim().replaceAll("[\\s-]+", "_").toUpperCase();
    }

    private static UserTypeEntity applyRequest(UserTypeEntity entity, CreateUserTypeRequest request) {
        entity.setName(request.name());
        entity.setCode(resolveCode(request.code(), request.name()));
        entity.setDescription(request.description());
        return entity;
    }
}
