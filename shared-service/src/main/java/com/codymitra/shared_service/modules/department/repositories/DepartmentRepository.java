package com.codymitra.shared_service.modules.department.repositories;

import java.util.UUID;
import com.codymitra.shared_service.modules.department.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByCode(String code);
}
