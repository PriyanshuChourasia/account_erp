package com.codymitra.shared_service.modules.company_financial_year.mappers;

import com.codymitra.shared_service.modules.company.entities.CompanyEntity;
import com.codymitra.shared_service.modules.company_financial_year.dtos.CompanyFinancialYearDTO;
import com.codymitra.shared_service.modules.company_financial_year.dtos.CreateCompanyFinancialYearDTO;
import com.codymitra.shared_service.modules.company_financial_year.entities.CompanyFinancialYearEntity;
import com.codymitra.shared_service.modules.financial_year.entities.FinancialYearEntity;

public final class CompanyFinancialYearMapper {

    public static CompanyFinancialYearDTO companyFinancialYearDTO(CompanyFinancialYearEntity companyFinancialYear) {
        return new CompanyFinancialYearDTO(
                companyFinancialYear.getId(),
                companyFinancialYear.getCompany().getId(),
                companyFinancialYear.getCompany().getName(),
                companyFinancialYear.getCompany().getCode(),
                companyFinancialYear.getFinancialYear().getId(),
                companyFinancialYear.getFinancialYear().getName(),
                companyFinancialYear.getFinancialYear().getCode(),
                companyFinancialYear.getFinancialYear().getStartDate(),
                companyFinancialYear.getFinancialYear().getEndDate(),
                companyFinancialYear.getFinancialYear().getIsCurrent(),
                companyFinancialYear.getActive()
        );
    }

    public static CompanyFinancialYearEntity companyFinancialYearEntity(CreateCompanyFinancialYearDTO request,
                                                                        CompanyEntity company,
                                                                        FinancialYearEntity financialYear) {
        CompanyFinancialYearEntity companyFinancialYear = new CompanyFinancialYearEntity();
        companyFinancialYear.setCompany(company);
        companyFinancialYear.setFinancialYear(financialYear);
        companyFinancialYear.setActive(true);
        return companyFinancialYear;
    }
}
