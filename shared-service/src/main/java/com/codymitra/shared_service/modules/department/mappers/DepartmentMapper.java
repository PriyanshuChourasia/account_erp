package com.codymitra.shared_service.modules.department.mappers;

import com.codymitra.shared_service.modules.department.dtos.CreateDepartmentRequest;
import com.codymitra.shared_service.modules.department.dtos.DepartmentDTO;
import com.codymitra.shared_service.modules.department.entities.DepartmentEntity;

public final class DepartmentMapper {

    public static DepartmentDTO departmentDTO(DepartmentEntity department) {

        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                department.getCode(),
                department.getParentId(),
                department.getDescription(),
                department.getActive()
        );
    }

    public static DepartmentEntity departmentEntity(CreateDepartmentRequest request) {
        DepartmentEntity department = new DepartmentEntity();
        department.setName(request.name());
        if (request.code() != null) {
            department.setCode(request.code().toUpperCase());
        }
        department.setDescription(request.description());
        department.setActive(true);
        return department;
    }

    public static DepartmentEntity departmentEntity(DepartmentEntity department, CreateDepartmentRequest request) {
        department.setName(request.name());
        department.setCode(request.code() != null ? request.code().toUpperCase() : null);
        department.setDescription(request.description());
        return department;
    }
}
