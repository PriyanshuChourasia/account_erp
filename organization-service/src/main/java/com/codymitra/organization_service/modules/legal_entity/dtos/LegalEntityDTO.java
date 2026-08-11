package com.codymitra.organization_service.modules.legal_entity.dtos;

public record LegalEntityDTO(
        Long id,
        String name,
        String code,
        String description
) {}
