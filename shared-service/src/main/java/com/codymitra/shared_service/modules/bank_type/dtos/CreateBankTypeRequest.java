package com.codymitra.shared_service.modules.bank_type.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateBankTypeRequest(
        @NotBlank(message = "Name is required")
        String name,
        String code,
        String description
) {}