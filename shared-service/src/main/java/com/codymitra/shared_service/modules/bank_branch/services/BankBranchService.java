package com.codymitra.shared_service.modules.bank_branch.services;

import com.codymitra.shared_service.modules.bank_branch.dtos.BankBranchDTO;
import com.codymitra.shared_service.modules.bank_branch.dtos.CreateBankBranchRequest;

import java.util.List;
import java.util.UUID;

public interface BankBranchService {
    List<BankBranchDTO> getAll();
    BankBranchDTO getById(UUID id);
    String create(CreateBankBranchRequest request);
}