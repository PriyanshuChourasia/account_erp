package com.codymitra.shared_service.modules.department_designation.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DepartmentDesignationDTO(
        UUID id,
        UUID departmentId,
        String departmentName,
        String departmentCode,
        UUID designationId,
        String designationName,
        String designationCode,
        Boolean active
) {
}
