package com.codymitra.shared_service.modules.application_feature.repositories;

import java.util.UUID;
import com.codymitra.shared_service.modules.application_feature.entities.ApplicationFeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationFeatureRepository extends JpaRepository<ApplicationFeatureEntity, UUID> {

    Boolean existsByName(String name);
}
