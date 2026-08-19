package com.codymitra.shared_service.modules.department.dtos;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DepartmentDTO(
        UUID id,
        String name,
        String code,
        UUID parentId,
        String description,
        Boolean active
) {
}
