package com.codymitra.shared_service.modules.gender.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateGenderRequest(
        @NotBlank(message = "Name is required")
        String name,

        String code,

        String description
) {
}
