package com.codymitra.shared_service.modules.salary_component.mappers;

import com.codymitra.shared_service.modules.salary_component.dtos.CreateSalaryComponentRequest;
import com.codymitra.shared_service.modules.salary_component.dtos.SalaryComponentDTO;
import com.codymitra.shared_service.modules.salary_component.entities.SalaryComponentEntity;
import com.codymitra.shared_service.modules.salary_component.enums.SalaryCalculationTypeEnum;

public final class SalaryComponentMapper {

    public static SalaryComponentDTO salaryComponentDTO(SalaryComponentEntity entity) {
        return new SalaryComponentDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getComponentType(),
                entity.getCalculationType(),
                entity.getAmount(),
                entity.getPercentage(),
                entity.getBaseComponentId(),
                entity.getTaxable(),
                entity.getAffectsNetSalary(),
                entity.getDisplayOrder(),
                entity.getActive()
        );
    }

    public static SalaryComponentEntity salaryComponentEntity(CreateSalaryComponentRequest request) {
        SalaryComponentEntity entity = applyRequest(new SalaryComponentEntity(), request);
        entity.setActive(true);
        return entity;
    }

    public static SalaryComponentEntity salaryComponentEntity(SalaryComponentEntity entity, CreateSalaryComponentRequest request) {
        return applyRequest(entity, request);
    }

    public static String resolveCode(String code, String name) {
        if (code != null && !code.isBlank()) {
            return code.trim().toUpperCase();
        }
        return name.trim().replaceAll("[\\s-]+", "_").toUpperCase();
    }

    private static SalaryComponentEntity applyRequest(SalaryComponentEntity entity, CreateSalaryComponentRequest request) {
        boolean percentage = request.calculationType() == SalaryCalculationTypeEnum.PERCENTAGE;

        entity.setName(request.name().trim());
        entity.setCode(resolveCode(request.code(), request.name()));
        entity.setDescription(request.description());
        entity.setComponentType(request.componentType());
        entity.setCalculationType(request.calculationType());
        entity.setAmount(request.calculationType() == SalaryCalculationTypeEnum.FLAT_AMOUNT ? request.amount() : null);
        entity.setPercentage(percentage ? request.percentage() : null);
        entity.setBaseComponentId(percentage ? request.baseComponentId() : null);
        entity.setTaxable(Boolean.TRUE.equals(request.taxable()));
        entity.setAffectsNetSalary(request.affectsNetSalary() == null || request.affectsNetSalary());
        entity.setDisplayOrder(request.displayOrder());
        return entity;
    }
}
