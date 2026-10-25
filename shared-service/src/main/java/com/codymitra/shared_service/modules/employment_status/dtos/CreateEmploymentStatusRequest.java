package com.codymitra.shared_service.modules.employment_status.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateEmploymentStatusRequest(
        @NotBlank(message = "Name is required")
        String name,

        String code,

        String description
) {
}
