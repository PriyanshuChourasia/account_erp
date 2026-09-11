package com.codymitra.shared_service.modules.bank.repositories;

import com.codymitra.shared_service.modules.bank.entities.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, UUID> {
    Boolean existsByName(String name);
    Boolean existsByCode(String code);
}