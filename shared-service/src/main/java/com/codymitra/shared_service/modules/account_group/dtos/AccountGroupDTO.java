package com.codymitra.shared_service.modules.account_group.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AccountGroupDTO(
        Long id,
        String name,
        String alias,
        String description,
        Boolean isActive
) {}
