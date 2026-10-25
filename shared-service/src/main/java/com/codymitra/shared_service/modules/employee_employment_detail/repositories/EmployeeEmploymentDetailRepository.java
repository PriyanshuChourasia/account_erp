package com.codymitra.shared_service.modules.employee_employment_detail.repositories;

import com.codymitra.shared_service.modules.employee_employment_detail.entities.EmployeeEmploymentDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeEmploymentDetailRepository extends JpaRepository<EmployeeEmploymentDetailEntity, UUID> {
}
