package com.codymitra.shared_service.modules.bank_type.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.bank_type.dtos.BankTypeDTO;
import com.codymitra.shared_service.modules.bank_type.dtos.CreateBankTypeRequest;
import com.codymitra.shared_service.modules.bank_type.entities.BankTypeEntity;
import com.codymitra.shared_service.modules.bank_type.mappers.BankTypeMapper;
import com.codymitra.shared_service.modules.bank_type.repositories.BankTypeRepository;
import com.codymitra.shared_service.modules.bank_type.services.BankTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankTypeServiceImpl implements BankTypeService {

    private final BankTypeRepository bankTypeRepository;

    @Override
    public List<BankTypeDTO> getAll() {
        return bankTypeRepository.findAll().stream()
                .map(BankTypeMapper::bankTypeDTO)
                .toList();
    }

    @Override
    public BankTypeDTO getById(UUID id) {
        BankTypeEntity entity = bankTypeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Bank type not found"));
        return BankTypeMapper.bankTypeDTO(entity);
    }

    @Override
    @Transactional
    public String create(CreateBankTypeRequest request) {
        if (bankTypeRepository.existsByName(request.name())) {
            throw new DataAlreadyExistsException("Bank type already exists with this name");
        }
        BankTypeEntity entity = BankTypeMapper.bankTypeEntity(request);
        bankTypeRepository.save(entity);
        return "Bank type created successfully";
    }
}