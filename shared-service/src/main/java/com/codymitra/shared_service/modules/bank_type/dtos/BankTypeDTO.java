package com.codymitra.shared_service.modules.bank_type.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BankTypeDTO(
        UUID id,
        String name,
        String code,
        String description,
        Boolean active
) {}