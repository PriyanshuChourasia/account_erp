package com.codymitra.shared_service.modules.account_group.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAccountGroupDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "Code is required")
        Long code,
        String alias,
        String description,
        Long parentId,
        @NotNull(message = "Code is required")
        Long accountNatureId
) {
}
