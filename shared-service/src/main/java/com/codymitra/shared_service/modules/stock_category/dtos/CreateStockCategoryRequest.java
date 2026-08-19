package com.codymitra.shared_service.modules.stock_category.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record CreateStockCategoryRequest(
        @NotBlank(message = "Name is required")
        String name,
        String code,
        String alias,
        UUID parentId,
        String description
) {
}
