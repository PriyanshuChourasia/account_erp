package com.codymitra.shared_service.modules.salary_component.repositories;

import com.codymitra.shared_service.modules.salary_component.entities.SalaryComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalaryComponentRepository extends JpaRepository<SalaryComponentEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByCode(String code);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCodeAndIdNot(String code, UUID id);

    Boolean existsByBaseComponentId(UUID baseComponentId);

    Optional<SalaryComponentEntity> findByCode(String code);

    List<SalaryComponentEntity> findAllByOrderByDisplayOrderAscNameAsc();
}
