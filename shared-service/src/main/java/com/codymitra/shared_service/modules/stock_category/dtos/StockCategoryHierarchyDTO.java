package com.codymitra.shared_service.modules.stock_category.dtos;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StockCategoryHierarchyDTO(
        UUID id,
        String name,
        String code,
        String alias,
        UUID parentId,
        String description,
        Boolean isActive,
        List<StockCategoryHierarchyDTO> children
) {}
