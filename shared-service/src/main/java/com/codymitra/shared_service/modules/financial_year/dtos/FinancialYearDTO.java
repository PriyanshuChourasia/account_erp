package com.codymitra.shared_service.modules.financial_year.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FinancialYearDTO(
        Long id,
        String name,
        String code,
        LocalDate startDate,
        LocalDate endDate,
        Boolean isCurrent
) {
}
