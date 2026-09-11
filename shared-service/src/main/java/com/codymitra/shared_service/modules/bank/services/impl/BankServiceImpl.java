package com.codymitra.shared_service.modules.bank.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.bank.dtos.BankDTO;
import com.codymitra.shared_service.modules.bank.dtos.CreateBankRequest;
import com.codymitra.shared_service.modules.bank.entities.BankEntity;
import com.codymitra.shared_service.modules.bank.mappers.BankMapper;
import com.codymitra.shared_service.modules.bank.repositories.BankRepository;
import com.codymitra.shared_service.modules.bank.services.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    @Override
    public List<BankDTO> getAll() {
        return bankRepository.findAll().stream()
                .map(BankMapper::bankDTO)
                .toList();
    }

    @Override
    public BankDTO getById(UUID id) {
        BankEntity entity = bankRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Bank not found"));
        return BankMapper.bankDTO(entity);
    }

    @Override
    @Transactional
    public String create(CreateBankRequest request) {
        if (bankRepository.existsByName(request.name())) {
            throw new DataAlreadyExistsException("Bank already exists with this name");
        }
        BankEntity entity = BankMapper.bankEntity(request);
        bankRepository.save(entity);
        return "Bank created successfully";
    }
}