package com.codymitra.organization_service.modules.operation_unit.repositories;

import java.util.UUID;
import com.codymitra.organization_service.modules.operation_unit.entities.OperationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationUnitRepository extends JpaRepository<OperationUnit, UUID> {
}
