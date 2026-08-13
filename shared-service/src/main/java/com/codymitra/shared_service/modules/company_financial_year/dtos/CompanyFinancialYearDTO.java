package com.codymitra.shared_service.modules.company_financial_year.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CompanyFinancialYearDTO(
        Long id,
        Long companyId,
        String companyName,
        String companyCode,
        Long financialYearId,
        String financialYearName,
        String financialYearCode,
        LocalDate startDate,
        LocalDate endDate,
        Boolean isCurrent,
        Boolean active
) {
}
