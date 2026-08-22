package com.codymitra.shared_service.modules.company.mappers;

import com.codymitra.shared_service.modules.company.dtos.CompanyDTO;
import com.codymitra.shared_service.modules.company.dtos.CreateCompanyDTO;
import com.codymitra.shared_service.modules.company.entities.CompanyEntity;
import com.codymitra.shared_service.modules.currency.entities.CurrencyEntity;
import com.codymitra.shared_service.modules.currency.mappers.CurrencyMapper;
import com.codymitra.shared_service.modules.currency_numbering_system.entities.CurrencyNumberingSystemEntity;
import com.codymitra.shared_service.modules.currency_numbering_system.mappers.CurrencyNumberingSystemMapper;

public final class CompanyMapper {

    public static CompanyDTO companyDTO(CompanyEntity companyEntity) {
        return new CompanyDTO(
                companyEntity.getId(),
                companyEntity.getName(),
                companyEntity.getParentId(),
                companyEntity.getCode(),
                companyEntity.getTelephone_no(),
                companyEntity.getMobile_no(),
                companyEntity.getFax_no(),
                companyEntity.getEmail(),
                companyEntity.getWebsite(),
                companyEntity.getMailingName(),
                companyEntity.getCurrency() != null ? companyEntity.getCurrency().getId() : null,
                companyEntity.getCurrency() != null ? CurrencyMapper.currencyDTO(companyEntity.getCurrency()) : null,
                companyEntity.getCurrencyNumberingSystemEntity() != null ? companyEntity.getCurrencyNumberingSystemEntity().getId() : null,
                companyEntity.getCurrencyNumberingSystemEntity() != null ? CurrencyNumberingSystemMapper.systemDTO(companyEntity.getCurrencyNumberingSystemEntity()) : null
        );
    }

    public static CompanyEntity companyEntity(CreateCompanyDTO request, CurrencyEntity currency, CurrencyNumberingSystemEntity numberingSystem) {
        return applyRequest(new CompanyEntity(), request, currency, numberingSystem);
    }

    public static CompanyEntity companyEntity(CompanyEntity company, CreateCompanyDTO request, CurrencyEntity currency, CurrencyNumberingSystemEntity numberingSystem) {
        return applyRequest(company, request, currency, numberingSystem);
    }

    private static CompanyEntity applyRequest(CompanyEntity company, CreateCompanyDTO request, CurrencyEntity currency, CurrencyNumberingSystemEntity numberingSystem) {
        company.setName(request.name());
        company.setParentId(request.parentId());
        company.setCode(resolveCode(request.code(), request.name()));
        company.setTelephone_no(request.telephoneNo());
        company.setMobile_no(request.mobileNo());
        company.setFax_no(request.faxNo());
        company.setEmail(request.email());
        company.setWebsite(request.website());
        company.setMailingName(request.mailingName() != null && !request.mailingName().isBlank() ? request.mailingName() : request.name());
        company.setCurrency(currency);
        company.setCurrencyNumberingSystemEntity(numberingSystem);
        return company;
    }

    /// derives the code from the company name when not supplied, e.g. "Prime Sys India" -> PRIME_SYS_INDIA
    public static String resolveCode(String code, String name) {
        if (code != null && !code.isBlank()) {
            return code;
        }
        return name.trim().replaceAll("[\\s-]+", "_").toUpperCase();
    }
}
