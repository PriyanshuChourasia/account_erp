package com.codymitra.shared_service.modules.employee_category.repositories;

import com.codymitra.shared_service.modules.employee_category.entities.EmployeeCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeCategoryRepository extends JpaRepository<EmployeeCategoryEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByAlias(String alias);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByAliasAndIdNot(String alias, UUID id);
}
