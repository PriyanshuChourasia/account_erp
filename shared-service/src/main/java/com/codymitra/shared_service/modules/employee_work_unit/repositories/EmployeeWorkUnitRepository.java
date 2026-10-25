package com.codymitra.shared_service.modules.employee_work_unit.repositories;

import com.codymitra.shared_service.modules.employee_work_unit.entities.EmployeeWorkUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeWorkUnitRepository extends JpaRepository<EmployeeWorkUnitEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsBySymbol(String symbol);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsBySymbolAndIdNot(String symbol, UUID id);

    java.util.Optional<EmployeeWorkUnitEntity> findBySymbol(String symbol);
}
