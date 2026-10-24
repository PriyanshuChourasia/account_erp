package com.codymitra.shared_service.modules.employee_category.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmployeeCategoryDTO(
        UUID id,
        String name,
        String alias,
        String description,
        Boolean allocateRevenueItem,
        Boolean allocateNonRevenueItem,
        Boolean active
) {
}
