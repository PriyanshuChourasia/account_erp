package com.codymitra.shared_service.modules.employment_type.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateEmploymentTypeRequest(
        @NotBlank(message = "Name is required")
        String name,

        String code,

        String description
) {
}
