package com.codymitra.shared_service.modules.employee.repositories;

import com.codymitra.shared_service.modules.employee.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

    Boolean existsByEmployeeCode(String employeeCode);

    Boolean existsByEmail(String email);

    Boolean existsByCompanyMail(String companyMail);

    Optional<EmployeeEntity> findByEmail(String email);

    Boolean existsByEmployeeCodeAndIdNot(String employeeCode, UUID id);

    Boolean existsByEmailAndIdNot(String email, UUID id);

    Boolean existsByCompanyMailAndIdNot(String companyMail, UUID id);

    List<EmployeeEntity> findByDepartmentId(UUID departmentId);

    List<EmployeeEntity> findByDesignationId(UUID designationId);
}
