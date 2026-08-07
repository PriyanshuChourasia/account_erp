package com.codymitra.organization_service.modules.operating_unit.repositories;

import com.codymitra.organization_service.modules.operating_unit.entities.OperatingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatingUnitRepository extends JpaRepository<OperatingUnit, Long> {
}
