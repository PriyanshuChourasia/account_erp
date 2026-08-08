package com.codymitra.shared_service.modules.inventory_type.repositories;

import com.codymitra.shared_service.modules.inventory_type.entities.InventoryTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryTypeRepository extends JpaRepository<InventoryTypeEntity, Long> {
}
