package com.codymitra.shared_service.modules.blood_group.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateBloodGroupRequest(
        @NotBlank(message = "Name is required")
        String name,
        String code,
        String description
) {}
