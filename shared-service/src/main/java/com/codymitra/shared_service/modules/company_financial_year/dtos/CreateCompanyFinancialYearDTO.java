package com.codymitra.shared_service.modules.company_financial_year.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateCompanyFinancialYearDTO(
        @NotBlank(message = "Company id is required")
        UUID companyId,
        @NotBlank(message = "Financial year id is required")
        UUID financialYearId
) {
}
