package com.codymitra.shared_service.modules.storage_location.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateStorageLocationRequestDTO(
        @NotBlank(message = "Name is required")
        String name,
        String code,
        String alias,
        Long parentId,
        String description
) {
}
