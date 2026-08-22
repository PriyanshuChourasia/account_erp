package com.codymitra.shared_service.modules.currency_minor_unit.repositories;

import java.util.UUID;

import com.codymitra.shared_service.modules.currency_minor_unit.entities.CurrencyMinorUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyMinorUnitRepository extends JpaRepository<CurrencyMinorUnitEntity, UUID> {

    Boolean existsByNameAndCurrencyId(String name, UUID currencyId);

    Boolean existsByNameAndCurrencyIdAndIdNot(String name, UUID currencyId, UUID id);
}
