package com.codymitra.shared_service.modules.financial_year.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateFinancialYearDTO(
        @NotBlank(message = "Name is required")
        String name,

        String code,

        @NotNull(message = "Start date is required")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate startDate,

        @NotNull(message = "End date is required")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate endDate,
        Boolean isCurrent
) {
}
