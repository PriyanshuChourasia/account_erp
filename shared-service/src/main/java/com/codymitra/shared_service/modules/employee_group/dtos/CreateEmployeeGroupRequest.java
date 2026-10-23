package com.codymitra.shared_service.modules.employee_group.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateEmployeeGroupRequest(
        @NotBlank(message = "Name is required")
        String name,

        String code,

        String description
) {
}
