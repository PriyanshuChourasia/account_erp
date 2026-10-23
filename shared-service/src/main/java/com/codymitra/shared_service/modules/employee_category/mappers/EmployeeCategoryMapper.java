package com.codymitra.shared_service.modules.employee_category.mappers;

import com.codymitra.shared_service.modules.employee_category.dtos.CreateEmployeeCategoryRequest;
import com.codymitra.shared_service.modules.employee_category.dtos.EmployeeCategoryDTO;
import com.codymitra.shared_service.modules.employee_category.entities.EmployeeCategoryEntity;

public final class EmployeeCategoryMapper {

    public static EmployeeCategoryDTO employeeCategoryDTO(EmployeeCategoryEntity entity) {
        return new EmployeeCategoryDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static EmployeeCategoryEntity employeeCategoryEntity(CreateEmployeeCategoryRequest request) {
        EmployeeCategoryEntity entity = applyRequest(new EmployeeCategoryEntity(), request);
        entity.setActive(true);
        return entity;
    }

    public static EmployeeCategoryEntity employeeCategoryEntity(EmployeeCategoryEntity entity, CreateEmployeeCategoryRequest request) {
        return applyRequest(entity, request);
    }

    public static String resolveCode(String code, String name) {
        if (code != null && !code.isBlank()) {
            return code.trim().toUpperCase();
        }
        return name.trim().replaceAll("[\\s-]+", "_").toUpperCase();
    }

    private static EmployeeCategoryEntity applyRequest(EmployeeCategoryEntity entity, CreateEmployeeCategoryRequest request) {
        entity.setName(request.name());
        entity.setCode(resolveCode(request.code(), request.name()));
        entity.setDescription(request.description());
        return entity;
    }
}
