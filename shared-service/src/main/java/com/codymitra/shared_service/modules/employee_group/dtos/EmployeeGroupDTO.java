package com.codymitra.shared_service.modules.employee_group.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmployeeGroupDTO(
        UUID id,
        String name,
        String code,
        Boolean active
) {
}
