package com.codymitra.shared_service.modules.company.dtos;

import com.codymitra.shared_service.modules.address.dtos.CreateAddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

public record CreateCompanyDTO(
        @NotBlank(message = "Name is required")
        String name,
        UUID parentId,
        String code,
        String telephoneNo,
        @NotBlank(message = "Mobile number is required")
        String mobileNo,
        String faxNo,
        String email,
        String website,
        String mailingName,
        UUID baseCurrencyId,
        UUID numberingSystemId,
        UUID financialYearId,
        LocalDate bookCommencingFrom,
        @Valid
        CreateAddressDTO address
) {}
