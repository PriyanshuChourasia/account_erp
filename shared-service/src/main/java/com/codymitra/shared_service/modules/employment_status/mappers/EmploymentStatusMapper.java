package com.codymitra.shared_service.modules.employment_status.mappers;

import com.codymitra.shared_service.modules.employment_status.dtos.CreateEmploymentStatusRequest;
import com.codymitra.shared_service.modules.employment_status.dtos.EmploymentStatusDTO;
import com.codymitra.shared_service.modules.employment_status.entities.EmploymentStatusEntity;

public final class EmploymentStatusMapper {

    public static EmploymentStatusDTO employmentStatusDTO(EmploymentStatusEntity entity) {
        return new EmploymentStatusDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static EmploymentStatusEntity employmentStatusEntity(CreateEmploymentStatusRequest request) {
        EmploymentStatusEntity entity = applyRequest(new EmploymentStatusEntity(), request);
        entity.setActive(true);
        return entity;
    }

    public static EmploymentStatusEntity employmentStatusEntity(EmploymentStatusEntity entity, CreateEmploymentStatusRequest request) {
        return applyRequest(entity, request);
    }

    public static String resolveCode(String code) {
        return code == null || code.isBlank() ? null : code.trim();
    }

    private static EmploymentStatusEntity applyRequest(EmploymentStatusEntity entity, CreateEmploymentStatusRequest request) {
        entity.setName(request.name().trim());
        entity.setCode(resolveCode(request.code()));
        entity.setDescription(request.description());
        return entity;
    }
}
