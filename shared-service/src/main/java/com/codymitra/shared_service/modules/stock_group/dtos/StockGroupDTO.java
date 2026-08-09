package com.codymitra.shared_service.modules.stock_group.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StockGroupDTO(
        Long id,
        String name,
        String code,
        String alias,
        String description,
        Boolean isActive,
        Boolean shouldAddQuantities,
        Boolean setAlterGstDetail
) {}
