package com.codymitra.shared_service.modules.blood_group.repositories;

import com.codymitra.shared_service.modules.blood_group.entities.BloodGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BloodGroupRepository extends JpaRepository<BloodGroupEntity, UUID> {
    Boolean existsByName(String name);
    Boolean existsByCode(String code);
}
