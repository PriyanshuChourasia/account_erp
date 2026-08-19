package com.codymitra.shared_service.modules.contact.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ContactDTO(
        UUID id,
        String name,
        String phone,
        String email,
        String designation,
        String description
) {}
