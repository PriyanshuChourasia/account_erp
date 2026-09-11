package com.codymitra.shared_service.modules.bank.services;

import com.codymitra.shared_service.modules.bank.dtos.BankDTO;
import com.codymitra.shared_service.modules.bank.dtos.CreateBankRequest;

import java.util.List;
import java.util.UUID;

public interface BankService {
    List<BankDTO> getAll();
    BankDTO getById(UUID id);
    String create(CreateBankRequest request);
}