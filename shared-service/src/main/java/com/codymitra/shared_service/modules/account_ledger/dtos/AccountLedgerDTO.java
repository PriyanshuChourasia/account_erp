package com.codymitra.shared_service.modules.account_ledger.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AccountLedgerDTO(
        UUID id,
        String name,
        String code,
        String description,
        Boolean active
) {
}
