package com.codymitra.shared_service.modules.company_financial_year.dtos;

import jakarta.validation.constraints.NotNull;

public record CreateCompanyFinancialYearDTO(
        @NotNull(message = "Company id is required")
        Long companyId,
        @NotNull(message = "Financial year id is required")
        Long financialYearId
) {
}
