package com.codymitra.shared_service.modules.storage_location.repositories;

import java.util.UUID;

import com.codymitra.shared_service.modules.storage_location.entities.StorageLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageLocationRepository extends JpaRepository<StorageLocationEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCode(String code);
}
