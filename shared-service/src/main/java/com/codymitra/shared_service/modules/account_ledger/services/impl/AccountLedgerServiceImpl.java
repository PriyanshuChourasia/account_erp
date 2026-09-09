package com.codymitra.shared_service.modules.account_ledger.services.impl;

import java.util.List;
import java.util.UUID;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.account_ledger.dtos.AccountLedgerDTO;
import com.codymitra.shared_service.modules.account_ledger.dtos.CreateAccountLedgerDTO;
import com.codymitra.shared_service.modules.account_ledger.entities.AccountLedgerEntity;
import com.codymitra.shared_service.modules.account_ledger.mappers.AccountLedgerMapper;
import com.codymitra.shared_service.modules.account_ledger.repositories.AccountLedgerRepository;
import com.codymitra.shared_service.modules.account_ledger.services.AccountLedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountLedgerServiceImpl implements AccountLedgerService {

    private final AccountLedgerRepository accountLedgerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AccountLedgerDTO> getAll() {
        return accountLedgerRepository.findAll().stream().map(AccountLedgerMapper::accountLedgerDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AccountLedgerDTO getById(UUID id) {
        return AccountLedgerMapper.accountLedgerDTO(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public AccountLedgerEntity getEntityById(UUID id) {
        return findById(id);
    }

    @Override
    @Transactional
    public AccountLedgerDTO create(CreateAccountLedgerDTO request) {
        validateUnique(request.name(), request.code(), null);
        AccountLedgerEntity saved = accountLedgerRepository.save(AccountLedgerMapper.accountLedgerEntity(request));
        return AccountLedgerMapper.accountLedgerDTO(saved);
    }

    @Override
    @Transactional
    public AccountLedgerDTO update(UUID id, CreateAccountLedgerDTO request) {
        findById(id);
        validateUnique(request.name(), request.code(), id);
        AccountLedgerEntity updated = accountLedgerRepository.save(
                AccountLedgerMapper.accountLedgerEntity(findById(id), request)
        );
        return AccountLedgerMapper.accountLedgerDTO(updated);
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        accountLedgerRepository.delete(findById(id));
        return "Account Ledger deleted successfully";
    }

    private void validateUnique(String name, String code, UUID id) {
        if (accountLedgerRepository.existsByName(name)) {
            throw new DataAlreadyExistsException("Account Ledger already exists with this name");
        }
        if (id != null && accountLedgerRepository.existsByNameAndIdNot(name, id)) {
            throw new DataAlreadyExistsException("Account Ledger already exists with this name");
        }
        if (code != null && !code.isBlank()) {
            if (accountLedgerRepository.existsByCode(code)) {
                throw new DataAlreadyExistsException("Account Ledger already exists with this code");
            }
            if (id != null && accountLedgerRepository.existsByCodeAndIdNot(code, id)) {
                throw new DataAlreadyExistsException("Account Ledger already exists with this code");
            }
        }
    }

    private AccountLedgerEntity findById(UUID id) {
        return accountLedgerRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Account Ledger does not exist with this id")
        );
    }
}
