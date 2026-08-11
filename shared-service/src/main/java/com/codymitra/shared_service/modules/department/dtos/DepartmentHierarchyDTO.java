package com.codymitra.shared_service.modules.department.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DepartmentHierarchyDTO(
        Long id,
        String name,
        String code,
        Long parentId,
        String description,
        Boolean active,
        List<DepartmentHierarchyDTO> children
) {
}
