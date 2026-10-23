package com.codymitra.shared_service.modules.department_designation.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateDepartmentDesignationRequest(
        @NotNull(message = "Department id is required")
        UUID departmentId,

        @NotNull(message = "Designation id is required")
        UUID designationId,

        Boolean active
) {
}
