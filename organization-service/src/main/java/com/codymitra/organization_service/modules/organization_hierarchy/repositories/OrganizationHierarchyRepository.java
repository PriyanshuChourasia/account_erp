package com.codymitra.organization_service.modules.organization_hierarchy.repositories;

import com.codymitra.organization_service.modules.organization_hierarchy.entities.OrganizationHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationHierarchyRepository extends JpaRepository<OrganizationHierarchy, Long> {
}
