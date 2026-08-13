package com.codymitra.shared_service.modules.uqc.repositories;

import com.codymitra.shared_service.modules.uqc.entities.UQCEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UQCRepository extends JpaRepository<UQCEntity, Long> {

    Boolean existsByName(String name);

    Boolean existsByCode(String code);
}
