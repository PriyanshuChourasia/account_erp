package com.codymitra.shared_service.modules.stock_group.dtos;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StockGroupDTO(
        UUID id,
        String name,
        String code,
        String alias,
        String description,
        Boolean isActive,
        Boolean shouldAddQuantities,
        Boolean setAlterGstDetail
) {}
