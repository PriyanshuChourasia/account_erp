package com.codymitra.shared_service.modules.storage_unit.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateStorageUnitDTO(
        @NotBlank(message = "Name is required")
        String name,
        Integer code,
        String description
) {}
