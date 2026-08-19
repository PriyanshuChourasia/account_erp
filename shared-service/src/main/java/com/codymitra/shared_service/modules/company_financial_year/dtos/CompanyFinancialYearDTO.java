package com.codymitra.shared_service.modules.company_financial_year.dtos;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CompanyFinancialYearDTO(
        UUID id,
        UUID companyId,
        String companyName,
        String companyCode,
        UUID financialYearId,
        String financialYearName,
        String financialYearCode,
        LocalDate startDate,
        LocalDate endDate,
        Boolean isCurrent,
        Boolean active
) {
}
