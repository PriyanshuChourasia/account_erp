package com.codymitra.shared_service.modules.account_group.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountGroupDTO(
        @NotBlank(message = "Name is required")
        String name,
        String alias,
        String description,
        Long parentId
) {
}
