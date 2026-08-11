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
        department.setParentId(request.parentId());
        department.setDescription(request.description());
        department.setActive(true);
        return department;
    }
}
