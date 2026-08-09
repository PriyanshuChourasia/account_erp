package com.codymitra.shared_service.modules.unit.repositories;


import com.codymitra.shared_service.modules.unit.entities.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity,Long> {

    Boolean existsByName(String name);
}
