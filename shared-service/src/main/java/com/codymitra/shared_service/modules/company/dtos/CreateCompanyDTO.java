package com.codymitra.shared_service.modules.company.dtos;

import com.codymitra.shared_service.modules.address.dtos.CreateAddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

public record CreateCompanyDTO(
        @NotBlank(message = "Name is required")
        String name,
        String code,
        @NotBlank(message = "Financial Year is required")
        UUID financialYearId,
        @NotBlank(message = "Books beginning from is required")
        LocalDate bookBeginFrom,
        String mailingName,
        @Valid
        CreateAddressDTO address
) {}
