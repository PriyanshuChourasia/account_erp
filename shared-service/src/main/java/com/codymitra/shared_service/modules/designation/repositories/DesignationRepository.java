package com.codymitra.shared_service.modules.designation.repositories;

import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<DesignationEntity, Long> {
}
