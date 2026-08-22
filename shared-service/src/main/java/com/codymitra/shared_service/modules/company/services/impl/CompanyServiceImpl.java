package com.codymitra.shared_service.modules.company.services.impl;

import java.util.List;
import java.util.UUID;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.address.services.AddressService;
import com.codymitra.shared_service.modules.company.dtos.CompanyDTO;
import com.codymitra.shared_service.modules.company.dtos.CreateCompanyDTO;
import com.codymitra.shared_service.modules.company.entities.CompanyEntity;
import com.codymitra.shared_service.modules.company.mappers.CompanyMapper;
import com.codymitra.shared_service.modules.company.repositories.CompanyRepository;
import com.codymitra.shared_service.modules.company.services.CompanyService;
import com.codymitra.shared_service.modules.company_financial_year.dtos.CreateCompanyFinancialYearDTO;
import com.codymitra.shared_service.modules.company_financial_year.services.CompanyFinancialYearService;
import com.codymitra.shared_service.modules.currency.entities.CurrencyEntity;
import com.codymitra.shared_service.modules.currency.services.CurrencyService;
import com.codymitra.shared_service.modules.currency_numbering_system.entities.CurrencyNumberingSystemEntity;
import com.codymitra.shared_service.modules.currency_numbering_system.services.CurrencyNumberingSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CurrencyService currencyService;
    private final CurrencyNumberingSystemService currencyNumberingSystemService;
    private final CompanyFinancialYearService companyFinancialYearService;
    private final AddressService addressService;

    @Override
    @Transactional(readOnly = true)
    public List<CompanyDTO> getAll() {
        return companyRepository.findAll().stream()
                .map(CompanyMapper::companyDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyDTO getById(UUID id) {
        return CompanyMapper.companyDTO(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyEntity getEntityById(UUID id) {
        return findById(id);
    }

    @Override
    @Transactional
    public CompanyDTO create(CreateCompanyDTO request) {
        validateUnique(request.name(), request.code(), null);

        CurrencyEntity currency = resolveCurrency(request.baseCurrencyId());
        CurrencyNumberingSystemEntity numberingSystem = resolveNumberingSystem(request.numberingSystemId());

        CompanyEntity saved = companyRepository.save(
                CompanyMapper.companyEntity(request, currency, numberingSystem)
        );

        if (request.address() != null) {
            addressService.create(request.address(), saved.getId().toString(), "COMPANY");
        }
        linkFinancialYear(saved.getId(), request.financialYearId(), request.bookCommencingFrom(), List.of());

        return CompanyMapper.companyDTO(saved);
    }

    @Override
    @Transactional
    public CompanyDTO update(UUID id, CreateCompanyDTO request) {
        findById(id);
        validateUnique(request.name(), request.code(), id);

        CurrencyEntity currency = resolveCurrency(request.baseCurrencyId());
        CurrencyNumberingSystemEntity numberingSystem = resolveNumberingSystem(request.numberingSystemId());

        CompanyEntity updated = companyRepository.save(
                CompanyMapper.companyEntity(findById(id), request, currency, numberingSystem)
        );
        linkFinancialYear(updated.getId(), request.financialYearId(), request.bookCommencingFrom(),
                companyFinancialYearService.getByCompanyId(id).stream()
                        .map(cfy -> cfy.financialYearId())
                        .toList());
        return CompanyMapper.companyDTO(updated);
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        companyRepository.delete(findById(id));
        return "Company deleted successfully";
    }

    private void linkFinancialYear(UUID companyId, UUID financialYearId, java.time.LocalDate bookCommencingFrom, List<UUID> existingFinancialYearIds) {
        if (financialYearId == null || existingFinancialYearIds.contains(financialYearId)) {
            return;
        }
        companyFinancialYearService.create(new CreateCompanyFinancialYearDTO(companyId, financialYearId, bookCommencingFrom));
    }

    private void validateUnique(String name, String code, UUID id) {
        String resolvedCode = CompanyMapper.resolveCode(code, name);
        if (companyRepository.existsByName(name)) {
            throw new DataAlreadyExistsException("Company already exists with this name");
        }
        if (id != null && companyRepository.existsByNameAndIdNot(name, id)) {
            throw new DataAlreadyExistsException("Company already exists with this name");
        }
        if (companyRepository.existsByCode(resolvedCode)) {
            throw new DataAlreadyExistsException("Company already exists with this code");
        }
        if (id != null && companyRepository.existsByCodeAndIdNot(resolvedCode, id)) {
            throw new DataAlreadyExistsException("Company already exists with this code");
        }
    }

    private CurrencyEntity resolveCurrency(UUID baseCurrencyId) {
        return baseCurrencyId != null ? currencyService.getEntityById(baseCurrencyId) : null;
    }

    private CurrencyNumberingSystemEntity resolveNumberingSystem(UUID numberingSystemId) {
        return numberingSystemId != null ? currencyNumberingSystemService.getEntityById(numberingSystemId) : null;
    }

    private CompanyEntity findById(UUID id) {
        return companyRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such company found")
        );
    }
}
