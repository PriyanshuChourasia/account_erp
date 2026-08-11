package com.codymitra.shared_service.modules.department.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DepartmentDTO(
        Long id,
        String name,
        String code,
        Long parentId,
        String description,
        Boolean active
) {
}
