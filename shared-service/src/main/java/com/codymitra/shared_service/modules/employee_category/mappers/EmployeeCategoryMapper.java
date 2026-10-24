package com.codymitra.shared_service.modules.employee_category.mappers;

import com.codymitra.shared_service.modules.employee_category.dtos.CreateEmployeeCategoryRequest;
import com.codymitra.shared_service.modules.employee_category.dtos.EmployeeCategoryDTO;
import com.codymitra.shared_service.modules.employee_category.entities.EmployeeCategoryEntity;

public final class EmployeeCategoryMapper {

    public static EmployeeCategoryDTO employeeCategoryDTO(EmployeeCategoryEntity entity) {
        return new EmployeeCategoryDTO(
                entity.getId(),
                entity.getName(),
                entity.getAlias(),
                entity.getDescription(),
                entity.getAllocateRevenueItem(),
                entity.getAllocateNonRevenueItem(),
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

    public static String resolveAlias(String alias) {
        return alias == null || alias.isBlank() ? null : alias.trim();
    }

    private static EmployeeCategoryEntity applyRequest(EmployeeCategoryEntity entity, CreateEmployeeCategoryRequest request) {
        entity.setName(request.name().trim());
        entity.setAlias(resolveAlias(request.alias()));
        entity.setDescription(request.description());
        entity.setAllocateRevenueItem(Boolean.TRUE.equals(request.allocateRevenueItem()));
        entity.setAllocateNonRevenueItem(Boolean.TRUE.equals(request.allocateNonRevenueItem()));
        return entity;
    }
}
