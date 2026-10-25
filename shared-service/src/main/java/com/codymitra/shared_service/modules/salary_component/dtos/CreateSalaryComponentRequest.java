package com.codymitra.shared_service.modules.salary_component.dtos;

import com.codymitra.shared_service.modules.salary_component.enums.SalaryCalculationTypeEnum;
import com.codymitra.shared_service.modules.salary_component.enums.SalaryComponentTypeEnum;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateSalaryComponentRequest(
        @NotBlank(message = "Name is required")
        String name,

        String code,

        String description,

        @NotNull(message = "Component type is required")
        SalaryComponentTypeEnum componentType,

        @NotNull(message = "Calculation type is required")
        SalaryCalculationTypeEnum calculationType,

        @DecimalMin(value = "0", message = "Amount cannot be negative")
        BigDecimal amount,

        @DecimalMin(value = "0", message = "Percentage cannot be negative")
        @DecimalMax(value = "100", message = "Percentage cannot be more than 100")
        BigDecimal percentage,

        UUID baseComponentId,

        Boolean taxable,

        Boolean affectsNetSalary,

        Integer displayOrder
) {
}
