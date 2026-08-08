package com.codymitra.shared_service.modules.stock_category.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StockCategoryDTO(
        Long id,
        String name,
        String code,
        String alias,
        String description,
        Boolean isActive
) {}
