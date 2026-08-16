package com.codymitra.shared_service.modules.uqc.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UQCDTO(
        Long id,
        String name,
        String code,
        String alias,
        String description
) {
}
