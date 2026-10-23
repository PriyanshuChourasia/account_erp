package com.codymitra.shared_service.modules.designation.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CreateDesignationRequest(
        @NotBlank(message = "Name is required")
        String name,

        String code,

        String parentId,

        String description
) {
}
