package com.codymitra.shared_service.modules.employee_group.repositories;

import com.codymitra.shared_service.modules.employee_group.entities.EmployeeGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeGroupRepository extends JpaRepository<EmployeeGroupEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByCode(String code);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCodeAndIdNot(String code, UUID id);
}
