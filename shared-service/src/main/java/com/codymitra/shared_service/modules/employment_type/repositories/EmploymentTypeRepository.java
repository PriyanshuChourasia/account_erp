package com.codymitra.shared_service.modules.employment_type.repositories;

import com.codymitra.shared_service.modules.employment_type.entities.EmploymentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmploymentTypeRepository extends JpaRepository<EmploymentTypeEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCode(String code);

    Boolean existsByCodeAndIdNot(String code, UUID id);
}
