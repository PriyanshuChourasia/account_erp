package com.codymitra.shared_service.modules.bank_type.repositories;

import com.codymitra.shared_service.modules.bank_type.entities.BankTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankTypeRepository extends JpaRepository<BankTypeEntity, UUID> {
    Boolean existsByName(String name);
    Boolean existsByCode(String code);
}