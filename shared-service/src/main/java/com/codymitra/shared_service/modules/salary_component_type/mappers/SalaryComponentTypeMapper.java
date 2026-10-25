package com.codymitra.shared_service.modules.salary_component_type.mappers;

import com.codymitra.shared_service.modules.salary_component_type.dtos.CreateSalaryComponentTypeRequest;
import com.codymitra.shared_service.modules.salary_component_type.dtos.SalaryComponentTypeDTO;
import com.codymitra.shared_service.modules.salary_component_type.entities.SalaryComponentTypeEntity;

public final class SalaryComponentTypeMapper {

    public static SalaryComponentTypeDTO salaryComponentTypeDTO(SalaryComponentTypeEntity entity) {
        return new SalaryComponentTypeDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static SalaryComponentTypeEntity salaryComponentTypeEntity(CreateSalaryComponentTypeRequest request) {
        SalaryComponentTypeEntity entity = applyRequest(new SalaryComponentTypeEntity(), request);
        entity.setActive(true);
        return entity;
    }

    public static SalaryComponentTypeEntity salaryComponentTypeEntity(SalaryComponentTypeEntity entity, CreateSalaryComponentTypeRequest request) {
        return applyRequest(entity, request);
    }

    public static String resolveCode(String code) {
        return code == null || code.isBlank() ? null : code.trim();
    }

    private static SalaryComponentTypeEntity applyRequest(SalaryComponentTypeEntity entity, CreateSalaryComponentTypeRequest request) {
        entity.setName(request.name().trim());
        entity.setCode(resolveCode(request.code()));
        entity.setDescription(request.description());
        return entity;
    }
}
