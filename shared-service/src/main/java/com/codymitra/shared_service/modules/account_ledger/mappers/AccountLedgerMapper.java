package com.codymitra.shared_service.modules.account_ledger.mappers;

import com.codymitra.shared_service.modules.account_ledger.dtos.AccountLedgerDTO;
import com.codymitra.shared_service.modules.account_ledger.dtos.CreateAccountLedgerDTO;
import com.codymitra.shared_service.modules.account_ledger.entities.AccountLedgerEntity;

public final class AccountLedgerMapper {

    private AccountLedgerMapper() {
    }

    public static AccountLedgerDTO accountLedgerDTO(AccountLedgerEntity accountLedgerEntity) {
        return new AccountLedgerDTO(
                accountLedgerEntity.getId(),
                accountLedgerEntity.getName(),
                accountLedgerEntity.getCode(),
                accountLedgerEntity.getDescription(),
                accountLedgerEntity.getActive()
        );
    }

    public static AccountLedgerEntity accountLedgerEntity(CreateAccountLedgerDTO request) {
        return applyRequest(new AccountLedgerEntity(), request);
    }

    public static AccountLedgerEntity accountLedgerEntity(AccountLedgerEntity accountLedgerEntity, CreateAccountLedgerDTO request) {
        return applyRequest(accountLedgerEntity, request);
    }

    private static AccountLedgerEntity applyRequest(AccountLedgerEntity accountLedgerEntity, CreateAccountLedgerDTO request) {
        accountLedgerEntity.setName(request.name());
        accountLedgerEntity.setCode(request.code());
        accountLedgerEntity.setDescription(request.description());
        accountLedgerEntity.setActive(request.active() != null ? request.active() : true);
        return accountLedgerEntity;
    }
}
