package com.codymitra.shared_service.modules.bank_type.services;

import com.codymitra.shared_service.modules.bank_type.dtos.BankTypeDTO;
import com.codymitra.shared_service.modules.bank_type.dtos.CreateBankTypeRequest;

import java.util.List;
import java.util.UUID;

public interface BankTypeService {
    List<BankTypeDTO> getAll();
    BankTypeDTO getById(UUID id);
    String create(CreateBankTypeRequest request);
}