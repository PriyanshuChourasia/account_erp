package com.codymitra.shared_service.modules.financial_year.mappers;

import com.codymitra.shared_service.modules.financial_year.dtos.CreateFinancialYearDTO;
import com.codymitra.shared_service.modules.financial_year.dtos.FinancialYearDTO;
import com.codymitra.shared_service.modules.financial_year.entities.FinancialYearEntity;

import java.time.LocalDate;

public final class FinancialYearMapper {

    public static FinancialYearDTO financialYearDTO(FinancialYearEntity financialYear) {
        return new FinancialYearDTO(
                financialYear.getId(),
                financialYear.getName(),
                financialYear.getCode(),
                financialYear.getStartDate(),
                financialYear.getEndDate(),
                financialYear.getIsCurrent()
        );
    }

    public static FinancialYearEntity financialYearEntity(CreateFinancialYearDTO request) {
        return applyRequest(new FinancialYearEntity(), request);
    }

    public static FinancialYearEntity financialYearEntity(FinancialYearEntity entity, CreateFinancialYearDTO request) {
        return applyRequest(entity, request);
    }

    private static FinancialYearEntity applyRequest(FinancialYearEntity entity, CreateFinancialYearDTO request) {
        entity.setName(request.name());
        entity.setCode(resolveCode(request.code(), request.startDate(), request.endDate()));
        entity.setStartDate(request.startDate());
        entity.setEndDate(request.endDate());
        if (request.isCurrent() != null) {
            entity.setIsCurrent(request.isCurrent());
        }
        return entity;
    }

    /// derives the code from the financial year period when not supplied, e.g. 01-04-2024 to 31-03-2025 -> 24-25
    public static String resolveCode(String code, LocalDate startDate, LocalDate endDate) {
        if (code != null && !code.isBlank()) {
            return code.toUpperCase();
        }
        return String.format("%02d-%02d", startDate.getYear() % 100, endDate.getYear() % 100);
    }
}
