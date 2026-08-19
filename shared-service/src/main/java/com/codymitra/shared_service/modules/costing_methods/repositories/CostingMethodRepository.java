package com.codymitra.shared_service.modules.costing_methods.repositories;

import java.util.UUID;

import com.codymitra.shared_service.modules.costing_methods.entities.CostingMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostingMethodRepository extends JpaRepository<CostingMethodEntity, UUID> {
}
