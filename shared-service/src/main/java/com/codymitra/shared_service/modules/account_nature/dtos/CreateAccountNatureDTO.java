package com.codymitra.shared_service.modules.account_nature.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountNatureDTO(
        @NotBlank(message = "Name is required")
        String name,
        Integer code,
        String description
) {}
