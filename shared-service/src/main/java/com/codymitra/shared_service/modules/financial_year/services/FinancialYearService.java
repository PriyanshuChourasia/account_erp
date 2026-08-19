package com.codymitra.shared_service.modules.financial_year.services;

import java.util.UUID;
import com.codymitra.shared_service.modules.financial_year.dtos.CreateFinancialYearDTO;
import com.codymitra.shared_service.modules.financial_year.dtos.FinancialYearDTO;

import java.util.List;

public interface FinancialYearService {

    List<FinancialYearDTO> getAll();

    FinancialYearDTO getById(UUID id);

    FinancialYearDTO create(CreateFinancialYearDTO request);

    FinancialYearDTO update(UUID id, CreateFinancialYearDTO request);

    String updateCurrentFinancialYear(UUID id, Boolean current);

    String delete(UUID id);
}
