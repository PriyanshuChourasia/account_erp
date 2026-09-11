package com.codymitra.shared_service.modules.bank_account_detail.repositories;

import com.codymitra.shared_service.modules.bank_account_detail.entities.BankAccountDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankAccountDetailRepository extends JpaRepository<BankAccountDetailEntity, UUID> {
    Boolean existsByAccountNumber(String accountNumber);
}