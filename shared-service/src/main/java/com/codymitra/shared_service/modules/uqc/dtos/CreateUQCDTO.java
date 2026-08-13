package com.codymitra.shared_service.modules.uqc.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUQCDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Code is required")
        @Size(max = 3, message = "Code cannot be greater than 3 characters")
        String code,
        String alias,
        String description
) {
}
