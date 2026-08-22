package com.codymitra.shared_service.modules.currency_numbering_units.repositories;

import java.util.Optional;
import java.util.UUID;

import com.codymitra.shared_service.modules.currency_numbering_units.entities.CurrencyNumberingUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyNumberingUnitRepository extends JpaRepository<CurrencyNumberingUnitEntity, UUID> {

    Boolean existsByNameAndNumberingSystemId(String name, UUID numberingSystemId);

    Boolean existsByNameAndNumberingSystemIdAndIdNot(String name, UUID numberingSystemId, UUID id);

    Optional<CurrencyNumberingUnitEntity> findTopByNumberingSystemIdOrderBySequenceDesc(UUID numberingSystemId);
}
