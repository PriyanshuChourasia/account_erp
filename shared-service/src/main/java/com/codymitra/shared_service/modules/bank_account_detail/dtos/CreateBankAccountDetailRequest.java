package com.codymitra.shared_service.modules.bank_account_detail.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateBankAccountDetailRequest(
        @NotBlank(message = "Bank name is required")
        String bankName,
        @NotBlank(message = "Account holder name is required")
        String accountHolderName,
        @NotBlank(message = "Account number is required")
        String accountNumber,
        String accountType,
        String branchName,
        String ifscCode,
        String currency
) {}