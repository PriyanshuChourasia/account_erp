package com.codymitra.shared_service.modules.application_module.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateApplicationModuleDTO(
        @NotBlank(message = "Name is required")
        String name,
        String description,
        String endpoint,
        Boolean isSystem
) {}
