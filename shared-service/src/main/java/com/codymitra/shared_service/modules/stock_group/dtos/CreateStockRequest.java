package com.codymitra.shared_service.modules.stock_group.dtos;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record CreateStockRequest(
        @NotBlank(message = "Name is required")
        String name,
        String alias,
        String code,
        String description,
        UUID parentId,
        Boolean shouldAddQuantities,
        Boolean setAlterGstDetail
) {
}