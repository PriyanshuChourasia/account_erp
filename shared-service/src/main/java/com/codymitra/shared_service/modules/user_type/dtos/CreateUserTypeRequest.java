package com.codymitra.shared_service.modules.user_type.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateUserTypeRequest(
        @NotBlank(message = "Name is required")
        String name,

        String code,

        String description
) {
}
