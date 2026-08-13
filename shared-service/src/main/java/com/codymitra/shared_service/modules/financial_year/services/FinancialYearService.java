package com.codymitra.shared_service.modules.financial_year.services;

import com.codymitra.shared_service.modules.financial_year.dtos.CreateFinancialYearDTO;
import com.codymitra.shared_service.modules.financial_year.dtos.FinancialYearDTO;

import java.util.List;

public interface FinancialYearService {

    List<FinancialYearDTO> getAll();

    FinancialYearDTO getById(Long id);

    FinancialYearDTO create(CreateFinancialYearDTO request);

    FinancialYearDTO update(Long id, CreateFinancialYearDTO request);

    String updateCurrentFinancialYear(Long id, Boolean current);

    String delete(Long id);
}
