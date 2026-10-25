package com.codymitra.shared_service.modules.salary_component_type.repositories;

import com.codymitra.shared_service.modules.salary_component_type.entities.SalaryComponentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalaryComponentTypeRepository extends JpaRepository<SalaryComponentTypeEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCode(String code);

    Boolean existsByCodeAndIdNot(String code, UUID id);
}
