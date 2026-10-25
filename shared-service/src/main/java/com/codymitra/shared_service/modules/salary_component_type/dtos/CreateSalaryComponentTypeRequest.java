package com.codymitra.shared_service.modules.salary_component_type.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateSalaryComponentTypeRequest(
        @NotBlank(message = "Name is required")
        String name,

        String code,

        String description
) {
}
