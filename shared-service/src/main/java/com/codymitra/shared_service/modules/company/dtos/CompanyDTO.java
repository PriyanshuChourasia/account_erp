package com.codymitra.shared_service.modules.company.dtos;

import com.codymitra.shared_service.modules.currency.dtos.CurrencyDTO;
import com.codymitra.shared_service.modules.currency_numbering_system.dtos.CurrencyNumberingSystemDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CompanyDTO(
        UUID id,
        String name,
        UUID parentId,
        String code,
        String telephoneNo,
        String mobileNo,
        String faxNo,
        String email,
        String website,
        String mailingName,
        UUID baseCurrencyId,
        CurrencyDTO baseCurrency,
        UUID numberingSystemId,
        CurrencyNumberingSystemDTO numberingSystem
) {}
