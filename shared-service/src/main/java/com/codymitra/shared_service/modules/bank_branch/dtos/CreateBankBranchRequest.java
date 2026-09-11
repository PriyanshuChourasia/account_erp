package com.codymitra.shared_service.modules.bank_branch.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateBankBranchRequest(
        @NotBlank(message = "Branch name is required")
        String branchName,
        String branchCode,
        String ifscCode,
        String address,
        @NotNull(message = "Bank id is required")
        UUID bankId
) {}