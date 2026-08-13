package com.codymitra.shared_service.modules.financial_year.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
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

    @Override
    public List<FinancialYearDTO> getAll() {
        return financialYearRepository.findAll().stream().map(FinancialYearMapper::financialYearDTO).toList();
    }

    @Override
    public FinancialYearDTO getById(Long id) {
        return FinancialYearMapper.financialYearDTO(findById(id));
    }

    @Override
    @Transactional
    public FinancialYearDTO create(CreateFinancialYearDTO request) {
        if (request.endDate().isBefore(request.startDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        if (financialYearRepository.existsByCode(request.code().toUpperCase())) {
            throw new DataAlreadyExistsException("Financial year with code " + request.code() + " already exists");
        }
        if (financialYearRepository.existsByName(request.name())) {
            throw new DataAlreadyExistsException("Financial year already exists");
        }
        boolean isCurrent = request.isCurrent() != null ? request.isCurrent() : false;
        if (isCurrent) {
            clearCurrentFlag();
        }
        FinancialYearEntity saved = financialYearRepository.save(FinancialYearMapper.financialYearEntity(request));
        return FinancialYearMapper.financialYearDTO(saved);
    }

    @Override
    @Transactional
    public FinancialYearDTO update(Long id, CreateFinancialYearDTO request) {
        FinancialYearEntity financialYear = findById(id);
        if (request.endDate().isBefore(request.startDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        if (financialYearRepository.existsByCode(request.code().toUpperCase()) && !financialYear.getCode().equalsIgnoreCase(request.code())) {
            throw new DataAlreadyExistsException("Financial year with code " + request.code() + " already exists");
        }
        if (financialYearRepository.existsByName(request.name()) && !financialYear.getName().equals(request.name())) {
            throw new DataAlreadyExistsException("Financial year already exists");
        }
        boolean isCurrent = request.isCurrent() != null ? request.isCurrent() : false;
        if (isCurrent) {
            clearCurrentFlag();
        }
        financialYear.setName(request.name());
        financialYear.setCode(request.code().toUpperCase());
        financialYear.setStartDate(request.startDate());
        financialYear.setEndDate(request.endDate());
        financialYear.setIsCurrent(isCurrent);
        return FinancialYearMapper.financialYearDTO(financialYearRepository.save(financialYear));
    }

    @Override
    public String delete(Long id) {
        FinancialYearEntity financialYear = findById(id);
        financialYearRepository.delete(financialYear);
        return "Financial year deleted successfully";
    }

    private void clearCurrentFlag() {
        List<FinancialYearEntity> currentYears = financialYearRepository.findByIsCurrentTrue();
        currentYears.forEach(year -> year.setIsCurrent(false));
        financialYearRepository.saveAll(currentYears);
    }

    private FinancialYearEntity findById(Long id) {
        return financialYearRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Financial year does not exist")
        );
    }
}
