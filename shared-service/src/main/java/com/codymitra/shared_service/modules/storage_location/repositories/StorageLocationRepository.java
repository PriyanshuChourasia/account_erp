package com.codymitra.shared_service.modules.storage_location.repositories;


import com.codymitra.shared_service.modules.storage_location.entities.StorageLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageLocationRepository extends JpaRepository<StorageLocationEntity,Long> {

    Boolean existsByName(String name);

    Boolean existsByNameAndIdNot(String name, Long id);

    Boolean existsByCode(String code);
}
