package com.codymitra.shared_service.modules.account_ledger.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountLedgerDTO(
        @NotBlank(message = "Name is required")
        String name,
        String code,
        String description,
        Boolean active
) {
}
