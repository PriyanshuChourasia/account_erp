package com.codymitra.organization_service.modules.legal_entity.dtos;

public record CreateLegalEntityDTO(
        String name,
        String code,
        String description,
        Long countryId
) { }
