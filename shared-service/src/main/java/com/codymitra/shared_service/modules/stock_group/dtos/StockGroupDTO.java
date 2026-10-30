package com.codymitra.shared_service.modules.stock_group.dtos;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StockGroupDTO(
        UUID id,
        String name,
        String alias,
        UUID parentId,
        String description,
        Boolean shouldAddQuantities,
        Boolean setAlterGstDetails,
        Boolean active
) {}
