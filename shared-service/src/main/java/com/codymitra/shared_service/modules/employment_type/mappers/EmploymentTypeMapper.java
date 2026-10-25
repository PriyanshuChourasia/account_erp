package com.codymitra.shared_service.modules.employment_type.mappers;

import com.codymitra.shared_service.modules.employment_type.dtos.CreateEmploymentTypeRequest;
import com.codymitra.shared_service.modules.employment_type.dtos.EmploymentTypeDTO;
import com.codymitra.shared_service.modules.employment_type.entities.EmploymentTypeEntity;

public final class EmploymentTypeMapper {

    public static EmploymentTypeDTO employmentTypeDTO(EmploymentTypeEntity entity) {
        return new EmploymentTypeDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static EmploymentTypeEntity employmentTypeEntity(CreateEmploymentTypeRequest request) {
        EmploymentTypeEntity entity = applyRequest(new EmploymentTypeEntity(), request);
        entity.setActive(true);
        return entity;
    }

    public static EmploymentTypeEntity employmentTypeEntity(EmploymentTypeEntity entity, CreateEmploymentTypeRequest request) {
        return applyRequest(entity, request);
    }

    public static String resolveCode(String code) {
        return code == null || code.isBlank() ? null : code.trim();
    }

    private static EmploymentTypeEntity applyRequest(EmploymentTypeEntity entity, CreateEmploymentTypeRequest request) {
        entity.setName(request.name().trim());
        entity.setCode(resolveCode(request.code()));
        entity.setDescription(request.description());
        return entity;
    }
}
