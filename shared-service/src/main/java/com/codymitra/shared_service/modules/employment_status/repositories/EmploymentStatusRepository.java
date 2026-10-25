package com.codymitra.shared_service.modules.employment_status.repositories;

import com.codymitra.shared_service.modules.employment_status.entities.EmploymentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmploymentStatusRepository extends JpaRepository<EmploymentStatusEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCode(String code);

    Boolean existsByCodeAndIdNot(String code, UUID id);
}
