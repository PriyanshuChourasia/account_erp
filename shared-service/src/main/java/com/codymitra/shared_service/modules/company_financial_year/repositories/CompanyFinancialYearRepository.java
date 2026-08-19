package com.codymitra.shared_service.modules.company_financial_year.repositories;

import java.util.UUID;
import com.codymitra.shared_service.modules.company_financial_year.entities.CompanyFinancialYearEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyFinancialYearRepository extends JpaRepository<CompanyFinancialYearEntity, UUID> {

    Boolean existsByCompany_IdAndFinancialYear_Id(UUID companyId, UUID financialYearId);

    List<CompanyFinancialYearEntity> findByCompany_Id(UUID companyId);
}
