package com.codymitra.shared_service.modules.employee_work_unit.dtos;

import com.codymitra.shared_service.enums.UnitTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmployeeWorkUnitDTO(
        UUID id,
        UnitTypeEnum unitType,
        String name,
        String symbol,
        String description,
        Boolean active
) {
}
