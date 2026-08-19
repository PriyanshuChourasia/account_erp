package com.codymitra.shared_service.modules.department.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record CreateDepartmentRequest(
        @NotBlank(message = "Name is required")
        String name,
        String code,
        UUID parentId,
        String description
) {
}
