package com.codymitra.shared_service.modules.bank_branch.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BankBranchDTO(
        UUID id,
        String branchName,
        String branchCode,
        String ifscCode,
        String address,
        UUID bankId,
        String bankName,
        Boolean active
) {}