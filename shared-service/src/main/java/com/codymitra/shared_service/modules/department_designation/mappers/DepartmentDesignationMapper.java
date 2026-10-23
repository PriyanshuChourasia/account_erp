package com.codymitra.shared_service.modules.department_designation.mappers;

import com.codymitra.shared_service.modules.department.entities.DepartmentEntity;
import com.codymitra.shared_service.modules.department_designation.dtos.CreateDepartmentDesignationRequest;
import com.codymitra.shared_service.modules.department_designation.dtos.DepartmentDesignationDTO;
import com.codymitra.shared_service.modules.department_designation.entities.DepartmentDesignationEntity;
import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;

public final class DepartmentDesignationMapper {

    public static DepartmentDesignationDTO departmentDesignationDTO(DepartmentDesignationEntity entity) {
        return new DepartmentDesignationDTO(
                entity.getId(),
                entity.getDepartment().getId(),
                entity.getDepartment().getName(),
                entity.getDepartment().getCode(),
                entity.getDesignation().getId(),
                entity.getDesignation().getName(),
                entity.getDesignation().getCode(),
                entity.getActive()
        );
    }

    public static DepartmentDesignationEntity departmentDesignationEntity(CreateDepartmentDesignationRequest request,
                                                                          DepartmentEntity department,
                                                                          DesignationEntity designation) {
        DepartmentDesignationEntity entity = applyRequest(new DepartmentDesignationEntity(), department, designation);
        entity.setActive(request.active() != null ? request.active() : true);
        return entity;
    }

    public static DepartmentDesignationEntity departmentDesignationEntity(DepartmentDesignationEntity entity,
                                                                          CreateDepartmentDesignationRequest request,
                                                                          DepartmentEntity department,
                                                                          DesignationEntity designation) {
        applyRequest(entity, department, designation);
        if (request.active() != null) {
            entity.setActive(request.active());
        }
        return entity;
    }

    private static DepartmentDesignationEntity applyRequest(DepartmentDesignationEntity entity,
                                                            DepartmentEntity department,
                                                            DesignationEntity designation) {
        entity.setDepartment(department);
        entity.setDesignation(designation);
        return entity;
    }
}
