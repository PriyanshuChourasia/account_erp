package com.codymitra.shared_service.modules.bank.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateBankRequest(
        @NotBlank(message = "Name is required")
        String name,
        String code,
        String description
) {}