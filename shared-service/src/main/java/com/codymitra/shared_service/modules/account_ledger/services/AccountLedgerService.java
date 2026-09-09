package com.codymitra.shared_service.modules.account_ledger.services;

import java.util.List;
import java.util.UUID;

import com.codymitra.shared_service.modules.account_ledger.dtos.AccountLedgerDTO;
import com.codymitra.shared_service.modules.account_ledger.dtos.CreateAccountLedgerDTO;
import com.codymitra.shared_service.modules.account_ledger.entities.AccountLedgerEntity;

public interface AccountLedgerService {

    List<AccountLedgerDTO> getAll();

    AccountLedgerDTO getById(UUID id);

    AccountLedgerEntity getEntityById(UUID id);

    AccountLedgerDTO create(CreateAccountLedgerDTO request);

    AccountLedgerDTO update(UUID id, CreateAccountLedgerDTO request);

    String delete(UUID id);
}
