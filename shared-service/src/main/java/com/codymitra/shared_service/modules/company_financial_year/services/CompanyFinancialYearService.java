package com.codymitra.shared_service.modules.company_financial_year.services;

import com.codymitra.shared_service.modules.company_financial_year.dtos.CompanyFinancialYearDTO;
import com.codymitra.shared_service.modules.company_financial_year.dtos.CreateCompanyFinancialYearDTO;

import java.util.List;

public interface CompanyFinancialYearService {

    List<CompanyFinancialYearDTO> getAll();

    CompanyFinancialYearDTO getById(Long id);

    List<CompanyFinancialYearDTO> getByCompanyId(Long companyId);

    CompanyFinancialYearDTO create(CreateCompanyFinancialYearDTO request);

    CompanyFinancialYearDTO update(Long id, CreateCompanyFinancialYearDTO request);

    String delete(Long id);
}
