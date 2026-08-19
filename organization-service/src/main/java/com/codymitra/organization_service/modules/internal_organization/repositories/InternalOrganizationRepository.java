package com.codymitra.organization_service.modules.internal_organization.repositories;

import java.util.UUID;
import com.codymitra.organization_service.modules.internal_organization.entities.InternalOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternalOrganizationRepository extends JpaRepository<InternalOrganization, UUID> {
}
