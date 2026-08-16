package com.codymitra.shared_service.modules.unique_quantity_code.repositories;

import com.codymitra.shared_service.modules.unique_quantity_code.entities.UniqueQuantityCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniqueQuantityCodeRepository extends JpaRepository<UniqueQuantityCodeEntity, Long> {

    Boolean existsByName(String name);

    Boolean existsByCode(String code);
}
