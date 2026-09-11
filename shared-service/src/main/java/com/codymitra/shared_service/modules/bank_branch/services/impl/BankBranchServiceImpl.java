package com.codymitra.shared_service.modules.bank_branch.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.bank.entities.BankEntity;
import com.codymitra.shared_service.modules.bank.repositories.BankRepository;
import com.codymitra.shared_service.modules.bank_branch.dtos.BankBranchDTO;
import com.codymitra.shared_service.modules.bank_branch.dtos.CreateBankBranchRequest;
import com.codymitra.shared_service.modules.bank_branch.entities.BankBranchEntity;
import com.codymitra.shared_service.modules.bank_branch.mappers.BankBranchMapper;
import com.codymitra.shared_service.modules.bank_branch.repositories.BankBranchRepository;
import com.codymitra.shared_service.modules.bank_branch.services.BankBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankBranchServiceImpl implements BankBranchService {

    private final BankBranchRepository bankBranchRepository;
    private final BankRepository bankRepository;

    @Override
    public List<BankBranchDTO> getAll() {
        return bankBranchRepository.findAll().stream()
                .map(BankBranchMapper::bankBranchDTO)
                .toList();
    }

    @Override
    public BankBranchDTO getById(UUID id) {
        BankBranchEntity entity = bankBranchRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Bank branch not found"));
        return BankBranchMapper.bankBranchDTO(entity);
    }

    @Override
    @Transactional
    public String create(CreateBankBranchRequest request) {
        if (bankBranchRepository.existsByBranchName(request.branchName())) {
            throw new DataAlreadyExistsException("Bank branch already exists with this name");
        }
        BankEntity bank = bankRepository.findById(request.bankId())
                .orElseThrow(() -> new DataNotFoundException("Bank not found"));
        BankBranchEntity entity = BankBranchMapper.bankBranchEntity(request);
        entity.setBank(bank);
        bankBranchRepository.save(entity);
        return "Bank branch created successfully";
    }
}