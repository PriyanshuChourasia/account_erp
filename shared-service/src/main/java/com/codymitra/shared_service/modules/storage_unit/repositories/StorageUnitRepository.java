package com.codymitra.shared_service.modules.storage_unit.repositories;

import com.codymitra.shared_service.modules.storage_unit.entities.StorageUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageUnitRepository extends JpaRepository<StorageUnitEntity,Long> {

    Boolean existsByName(String name);
}
