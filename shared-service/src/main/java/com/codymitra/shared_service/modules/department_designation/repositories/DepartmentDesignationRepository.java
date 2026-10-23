package com.codymitra.shared_service.modules.department_designation.repositories;

import com.codymitra.shared_service.modules.department_designation.entities.DepartmentDesignationEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DepartmentDesignationRepository extends JpaRepository<DepartmentDesignationEntity, UUID> {

    // load department + designation in the same query (avoids N+1 in the mapper)
    @Override
    @EntityGraph(attributePaths = {"department", "designation"})
    List<DepartmentDesignationEntity> findAll();

    @EntityGraph(attributePaths = {"department", "designation"})
    List<DepartmentDesignationEntity> findByDepartment_Id(UUID departmentId);

    @EntityGraph(attributePaths = {"department", "designation"})
    List<DepartmentDesignationEntity> findByDesignation_Id(UUID designationId);

    Boolean existsByDepartment_IdAndDesignation_Id(UUID departmentId, UUID designationId);

    Boolean existsByDepartment_IdAndDesignation_IdAndIdNot(UUID departmentId, UUID designationId, UUID id);

    Boolean existsByDesignation_Id(UUID designationId);
}
