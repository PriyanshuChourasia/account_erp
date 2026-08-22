package com.codymitra.shared_service.modules.company.repositories;

import java.util.UUID;
import com.codymitra.shared_service.modules.company.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByCode(String code);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCodeAndIdNot(String code, UUID id);
}
