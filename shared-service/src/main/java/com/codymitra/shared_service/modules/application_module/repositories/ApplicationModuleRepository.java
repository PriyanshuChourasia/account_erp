package com.codymitra.shared_service.modules.application_module.repositories;

import java.util.UUID;
import com.codymitra.shared_service.modules.application_module.entities.ApplicationModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationModuleRepository extends JpaRepository<ApplicationModuleEntity, UUID> {

    Boolean existsByName(String name);
}
