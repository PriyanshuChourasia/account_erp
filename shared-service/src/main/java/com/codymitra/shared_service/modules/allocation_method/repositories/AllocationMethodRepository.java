package com.codymitra.shared_service.modules.allocation_method.repositories;

import com.codymitra.shared_service.modules.allocation_method.entities.AllocationMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AllocationMethodRepository extends JpaRepository<AllocationMethodEntity, UUID> {
    Boolean existsByName(String name);
    Boolean existsByCode(String code);
}
