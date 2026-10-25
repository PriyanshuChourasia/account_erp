package com.codymitra.shared_service.modules.file_type.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateFileTypeRequest(
        @NotBlank(message = "Name is required")
        String name,

        String code,

        String description
) {
}