package com.codymitra.shared_service.modules.tax_rate_master.repositories;

import java.util.UUID;

import com.codymitra.shared_service.modules.tax_rate_master.entities.TaxRateMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRateMasterRepository extends JpaRepository<TaxRateMasterEntity, UUID> {
}
