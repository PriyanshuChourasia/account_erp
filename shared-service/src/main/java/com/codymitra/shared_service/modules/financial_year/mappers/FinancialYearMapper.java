package com.codymitra.shared_service.modules.financial_year.mappers;

import com.codymitra.shared_service.modules.financial_year.dtos.CreateFinancialYearDTO;
import com.codymitra.shared_service.modules.financial_year.dtos.FinancialYearDTO;
import com.codymitra.shared_service.modules.financial_year.entities.FinancialYearEntity;

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
        FinancialYearEntity financialYear = new FinancialYearEntity();
        financialYear.setName(request.name());
        financialYear.setCode(request.code().toUpperCase());
        financialYear.setStartDate(request.startDate());
        financialYear.setEndDate(request.endDate());
        financialYear.setIsCurrent(request.isCurrent() != null ? request.isCurrent() : false);
        return financialYear;
    }
}
