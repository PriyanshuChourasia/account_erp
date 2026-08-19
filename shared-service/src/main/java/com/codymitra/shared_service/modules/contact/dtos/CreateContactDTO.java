package com.codymitra.shared_service.modules.contact.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateContactDTO(
        @NotBlank(message = "Name is required")
        String name,
        String phone,
        String email,
        String designation,
        String description
) {}
