package com.codymitra.shared_service.modules.company_financial_year.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CreateCompanyFinancialYearDTO(
        @NotNull(message = "Company id is required")
        UUID companyId,
        @NotNull(message = "Financial year id is required")
        UUID financialYearId,
        /// optional; defaults to the financial year start date when omitted
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate bookCommencingFrom
) {
}
