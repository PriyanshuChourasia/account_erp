package com.codymitra.shared_service.modules.company.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CompanyDTO(
        UUID id,
        String name,
        String code
) {}
