package com.codymitra.shared_service.modules.company_financial_year.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.company.entities.CompanyEntity;
import com.codymitra.shared_service.modules.company.repositories.CompanyRepository;
import com.codymitra.shared_service.modules.company_financial_year.dtos.CompanyFinancialYearDTO;
import com.codymitra.shared_service.modules.company_financial_year.dtos.CreateCompanyFinancialYearDTO;
import com.codymitra.shared_service.modules.company_financial_year.entities.CompanyFinancialYearEntity;
import com.codymitra.shared_service.modules.company_financial_year.mappers.CompanyFinancialYearMapper;
import com.codymitra.shared_service.modules.company_financial_year.repositories.CompanyFinancialYearRepository;
import com.codymitra.shared_service.modules.company_financial_year.services.CompanyFinancialYearService;
import com.codymitra.shared_service.modules.financial_year.entities.FinancialYearEntity;
import com.codymitra.shared_service.modules.financial_year.repositories.FinancialYearRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyFinancialYearServiceImpl implements CompanyFinancialYearService {

    private final CompanyFinancialYearRepository companyFinancialYearRepository;
    private final CompanyRepository companyRepository;
    private final FinancialYearRepository financialYearRepository;

    @Override
    public List<CompanyFinancialYearDTO> getAll() {
        return companyFinancialYearRepository.findAll().stream()
                .map(CompanyFinancialYearMapper::companyFinancialYearDTO)
                .toList();
    }

    @Override
    public CompanyFinancialYearDTO getById(Long id) {
        return CompanyFinancialYearMapper.companyFinancialYearDTO(findById(id));
    }

    @Override
    public List<CompanyFinancialYearDTO> getByCompanyId(Long companyId) {
        return companyFinancialYearRepository.findByCompany_Id(companyId).stream()
                .map(CompanyFinancialYearMapper::companyFinancialYearDTO)
                .toList();
    }

    @Override
    public CompanyFinancialYearDTO create(CreateCompanyFinancialYearDTO request) {
        CompanyEntity company = companyRepository.findById(request.companyId()).orElseThrow(
                () -> new DataNotFoundException("No such company exists")
        );
        FinancialYearEntity financialYear = financialYearRepository.findById(request.financialYearId()).orElseThrow(
                () -> new DataNotFoundException("No such financial year exists")
        );
        if (companyFinancialYearRepository.existsByCompany_IdAndFinancialYear_Id(request.companyId(), request.financialYearId())) {
            throw new DataAlreadyExistsException("Company financial year already exists");
        }
        CompanyFinancialYearEntity saved = companyFinancialYearRepository.save(
                CompanyFinancialYearMapper.companyFinancialYearEntity(request, company, financialYear)
        );
        return CompanyFinancialYearMapper.companyFinancialYearDTO(saved);
    }

    @Override
    public CompanyFinancialYearDTO update(Long id, CreateCompanyFinancialYearDTO request) {
        CompanyFinancialYearEntity companyFinancialYear = findById(id);
        CompanyEntity company = companyRepository.findById(request.companyId()).orElseThrow(
                () -> new DataNotFoundException("No such company exists")
        );
        FinancialYearEntity financialYear = financialYearRepository.findById(request.financialYearId()).orElseThrow(
                () -> new DataNotFoundException("No such financial year exists")
        );
        boolean sameAssociation = companyFinancialYear.getCompany().getId().equals(request.companyId())
                && companyFinancialYear.getFinancialYear().getId().equals(request.financialYearId());
        if (!sameAssociation
                && companyFinancialYearRepository.existsByCompany_IdAndFinancialYear_Id(request.companyId(), request.financialYearId())) {
            throw new DataAlreadyExistsException("Company financial year already exists");
        }
        companyFinancialYear.setCompany(company);
        companyFinancialYear.setFinancialYear(financialYear);
        return CompanyFinancialYearMapper.companyFinancialYearDTO(companyFinancialYearRepository.save(companyFinancialYear));
    }

    @Override
    public String delete(Long id) {
        CompanyFinancialYearEntity companyFinancialYear = findById(id);
        companyFinancialYear.setActive(false);
        companyFinancialYearRepository.save(companyFinancialYear);
        return "Company financial year deactivated successfully";
    }

    private CompanyFinancialYearEntity findById(Long id) {
        return companyFinancialYearRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Company financial year does not exist")
        );
    }
}
