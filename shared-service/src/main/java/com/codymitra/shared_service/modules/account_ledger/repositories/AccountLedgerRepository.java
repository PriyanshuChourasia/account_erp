package com.codymitra.shared_service.modules.account_ledger.repositories;

import java.util.UUID;

import com.codymitra.shared_service.modules.account_ledger.entities.AccountLedgerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountLedgerRepository extends JpaRepository<AccountLedgerEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByCode(String code);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCodeAndIdNot(String code, UUID id);
}
