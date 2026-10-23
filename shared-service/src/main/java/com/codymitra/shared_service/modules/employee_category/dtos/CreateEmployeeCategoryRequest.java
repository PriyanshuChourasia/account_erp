package com.codymitra.shared_service.modules.employee_category.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateEmployeeCategoryRequest(
        @NotBlank(message = "Name is required")
        String name,

        String code,

        String description
) {
}
