package com.codymitra.shared_service.modules.currency_numbering_system.repositories;

import java.util.UUID;

import com.codymitra.shared_service.modules.currency_numbering_system.entities.CurrencyNumberingSystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyNumberingSystemRepository extends JpaRepository<CurrencyNumberingSystemEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCode(String code);

    Boolean existsByCodeAndIdNot(String code, UUID id);
}
