package com.codymitra.shared_service.modules.employee_work_unit.dtos;

import com.codymitra.shared_service.enums.UnitTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEmployeeWorkUnitRequest(
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Unit type is required")
        UnitTypeEnum unitType,

        @NotBlank(message = "Symbol is required")
        String symbol,

        String description
) {
}
