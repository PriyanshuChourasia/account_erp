package com.codymitra.shared_service.modules.salary_component.dtos;

import com.codymitra.shared_service.modules.salary_component.enums.SalaryCalculationTypeEnum;
import com.codymitra.shared_service.modules.salary_component.enums.SalaryComponentTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SalaryComponentDTO(
        UUID id,
        String name,
        String code,
        String description,
        SalaryComponentTypeEnum componentType,
        SalaryCalculationTypeEnum calculationType,
        BigDecimal amount,
        BigDecimal percentage,
        UUID baseComponentId,
        Boolean taxable,
        Boolean affectsNetSalary,
        Integer displayOrder,
        Boolean active
) {
}
