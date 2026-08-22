package com.codymitra.shared_service.modules.financial_year.services.impl;

import java.util.UUID;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.country.entities.CountryEntity;
import com.codymitra.shared_service.modules.country.services.CountryService;
import com.codymitra.shared_service.modules.financial_year.dtos.CreateFinancialYearDTO;
import com.codymitra.shared_service.modules.financial_year.dtos.FinancialYearDTO;
import com.codymitra.shared_service.modules.financial_year.entities.FinancialYearEntity;
import com.codymitra.shared_service.modules.financial_year.mappers.FinancialYearMapper;
import com.codymitra.shared_service.modules.financial_year.repositories.FinancialYearRepository;
import com.codymitra.shared_service.modules.financial_year.services.FinancialYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialYearServiceImpl implements FinancialYearService {

    private final FinancialYearRepository financialYearRepository;
    private final CountryService countryService;

    @Override
    @Transactional(readOnly = true)
    public List<FinancialYearDTO> getAll() {
        return financialYearRepository.findAll().stream().map(FinancialYearMapper::financialYearDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FinancialYearDTO getById(UUID id) {
        return FinancialYearMapper.financialYearDTO(findById(id));
    }

    @Override
    @Transactional
    public FinancialYearDTO create(CreateFinancialYearDTO request) {
        validateRequest(request, null);

        boolean isCurrent = request.isCurrent() != null ? request.isCurrent() : false;
        if (isCurrent) {
            clearCurrentFlag();
        }
        CountryEntity country = countryService.getEntityById(request.countryId());
        FinancialYearEntity saved = financialYearRepository.save(FinancialYearMapper.financialYearEntity(request, country));
        return FinancialYearMapper.financialYearDTO(saved);
    }

    @Override
    @Transactional
    public FinancialYearDTO update(UUID id, CreateFinancialYearDTO request) {
        findById(id);
        validateRequest(request, id);

        boolean isCurrent = request.isCurrent() != null ? request.isCurrent() : false;
        if (isCurrent) {
            clearCurrentFlag();
        }
        CountryEntity country = countryService.getEntityById(request.countryId());
        FinancialYearEntity updated = financialYearRepository.save(
                FinancialYearMapper.financialYearEntity(findById(id), request, country)
        );
        return FinancialYearMapper.financialYearDTO(updated);
    }

    @Override
    @Transactional
    public String updateCurrentFinancialYear(UUID id, Boolean current) {
        FinancialYearEntity financialYear = findById(id);
        if (Boolean.TRUE.equals(current)) {
            clearCurrentFlag();
        }
        financialYear.setIsCurrent(current);
        financialYearRepository.save(financialYear);
        return "Financial year updated successfully";
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        financialYearRepository.delete(findById(id));
        return "Financial year deleted successfully";
    }

    private void validateRequest(CreateFinancialYearDTO request, UUID id) {
        if (request.endDate().isBefore(request.startDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        String code = FinancialYearMapper.resolveCode(request.code(), request.startDate(), request.endDate());
        if (id == null ? financialYearRepository.existsByName(request.name()) : financialYearRepository.existsByNameAndIdNot(request.name(), id)) {
            throw new DataAlreadyExistsException("Financial year already exists");
        }
        if (id == null ? financialYearRepository.existsByCode(code) : financialYearRepository.existsByCodeAndIdNot(code, id)) {
            throw new DataAlreadyExistsException("Financial year with code " + code + " already exists");
        }
        if (id == null && financialYearRepository.existsByStartDateAndEndDate(request.startDate(), request.endDate())) {
            throw new DataAlreadyExistsException("Start Date and End Date already exists");
        }
    }

    private void clearCurrentFlag() {
        List<FinancialYearEntity> currentYears = financialYearRepository.findByIsCurrentTrue();
        currentYears.forEach(year -> year.setIsCurrent(false));
        financialYearRepository.saveAll(currentYears);
    }

    private FinancialYearEntity findById(UUID id) {
        return financialYearRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such financial year found")
        );
    }
}
