package com.codymitra.shared_service.modules.company.repositories;

import com.codymitra.shared_service.modules.company.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    Boolean existsByName(String name);
}
