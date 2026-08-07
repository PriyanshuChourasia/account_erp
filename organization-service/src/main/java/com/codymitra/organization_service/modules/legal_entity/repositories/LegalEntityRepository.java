package com.codymitra.organization_service.modules.legal_entity.repositories;

import com.codymitra.organization_service.modules.legal_entity.entities.LegalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalEntityRepository extends JpaRepository<LegalEntity, Long> {
}
