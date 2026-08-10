package com.codymitra.shared_service.modules.stock_category.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StockCategoryHierarchyDTO(
        Long id,
        String name,
        String code,
        String alias,
        Long parentId,
        String description,
        Boolean isActive,
        List<StockCategoryHierarchyDTO> children
) {}
