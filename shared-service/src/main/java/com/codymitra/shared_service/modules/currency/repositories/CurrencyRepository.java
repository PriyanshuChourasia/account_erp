package com.codymitra.shared_service.modules.currency.repositories;

import java.util.UUID;

import com.codymitra.shared_service.modules.currency.entities.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByCurrencySymbol(String currencySymbol);
}
