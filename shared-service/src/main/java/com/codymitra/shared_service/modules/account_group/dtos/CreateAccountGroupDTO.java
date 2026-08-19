package com.codymitra.shared_service.modules.account_group.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateAccountGroupDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "Code is required")
        Long code,
        String alias,
        String description,
        UUID parentId,
        @NotBlank(message = "Account Nature is required")
        UUID accountNatureId
) {
}
