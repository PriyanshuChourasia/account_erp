package com.codymitra.shared_service.modules.company_financial_year.services;

import java.util.UUID;
import com.codymitra.shared_service.modules.company_financial_year.dtos.CompanyFinancialYearDTO;
import com.codymitra.shared_service.modules.company_financial_year.dtos.CreateCompanyFinancialYearDTO;

import java.util.List;

public interface CompanyFinancialYearService {

    List<CompanyFinancialYearDTO> getAll();

    CompanyFinancialYearDTO getById(UUID id);

    List<CompanyFinancialYearDTO> getByCompanyId(UUID companyId);

    CompanyFinancialYearDTO create(CreateCompanyFinancialYearDTO request);

    CompanyFinancialYearDTO update(UUID id, CreateCompanyFinancialYearDTO request);

    String delete(UUID id);
}
