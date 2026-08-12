package com.codymitra.shared_service.modules.accounting_nature.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AccountNatureDTO(
        Long id,
        String name,
        Integer code,
        String description
) {}
