package com.codymitra.shared_service.modules.bank_account_detail.services;

import com.codymitra.shared_service.modules.bank_account_detail.dtos.BankAccountDetailDTO;
import com.codymitra.shared_service.modules.bank_account_detail.dtos.CreateBankAccountDetailRequest;

import java.util.List;
import java.util.UUID;

public interface BankAccountDetailService {
    List<BankAccountDetailDTO> getAll();
    BankAccountDetailDTO getById(UUID id);
    String create(CreateBankAccountDetailRequest request);
}