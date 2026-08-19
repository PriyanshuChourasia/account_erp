package com.codymitra.organization_service.modules.legal_entity.dtos;

import java.util.UUID;
public record LegalEntityDTO(
        UUID id,
        String name,
        String code,
        String description
) {}
