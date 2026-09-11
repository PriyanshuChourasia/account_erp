package com.codymitra.shared_service.modules.bank_account_detail.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BankAccountDetailDTO(
        UUID id,
        String bankName,
        String accountHolderName,
        String accountNumber,
        String accountType,
        String branchName,
        String ifscCode,
        String currency,
        Boolean active
) {}