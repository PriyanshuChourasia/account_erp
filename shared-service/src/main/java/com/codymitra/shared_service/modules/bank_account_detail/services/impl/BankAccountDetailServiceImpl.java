package com.codymitra.shared_service.modules.bank_account_detail.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.bank_account_detail.dtos.BankAccountDetailDTO;
import com.codymitra.shared_service.modules.bank_account_detail.dtos.CreateBankAccountDetailRequest;
import com.codymitra.shared_service.modules.bank_account_detail.entities.BankAccountDetailEntity;
import com.codymitra.shared_service.modules.bank_account_detail.mappers.BankAccountDetailMapper;
import com.codymitra.shared_service.modules.bank_account_detail.repositories.BankAccountDetailRepository;
import com.codymitra.shared_service.modules.bank_account_detail.services.BankAccountDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankAccountDetailServiceImpl implements BankAccountDetailService {

    private final BankAccountDetailRepository bankAccountDetailRepository;

    @Override
    public List<BankAccountDetailDTO> getAll() {
        return bankAccountDetailRepository.findAll().stream()
                .map(BankAccountDetailMapper::bankAccountDetailDTO)
                .toList();
    }

    @Override
    public BankAccountDetailDTO getById(UUID id) {
        BankAccountDetailEntity entity = bankAccountDetailRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Bank account detail not found"));
        return BankAccountDetailMapper.bankAccountDetailDTO(entity);
    }

    @Override
    @Transactional
    public String create(CreateBankAccountDetailRequest request) {
        if (bankAccountDetailRepository.existsByAccountNumber(request.accountNumber())) {
            throw new DataAlreadyExistsException("Bank account detail already exists with this account number");
        }
        BankAccountDetailEntity entity = BankAccountDetailMapper.bankAccountDetailEntity(request);
        bankAccountDetailRepository.save(entity);
        return "Bank account detail created successfully";
    }
}