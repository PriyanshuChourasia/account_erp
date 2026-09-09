package com.codymitra.shared_service.modules.allocation_method.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateAllocationMethodRequest(
        @NotBlank(message = "Name is required")
        String name,
        String code,
        String description
) {}
