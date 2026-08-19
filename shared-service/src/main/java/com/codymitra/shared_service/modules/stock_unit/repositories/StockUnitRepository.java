package com.codymitra.shared_service.modules.stock_unit.repositories;

import java.util.UUID;

import com.codymitra.shared_service.modules.stock_unit.entities.StockUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockUnitRepository extends JpaRepository<StockUnitEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByNameAndIdNot(String name, UUID id);
}
