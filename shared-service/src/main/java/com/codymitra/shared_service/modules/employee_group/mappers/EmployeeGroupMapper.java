package com.codymitra.shared_service.modules.employee_group.mappers;

import com.codymitra.shared_service.modules.employee_group.dtos.CreateEmployeeGroupRequest;
import com.codymitra.shared_service.modules.employee_group.dtos.EmployeeGroupDTO;
import com.codymitra.shared_service.modules.employee_group.entities.EmployeeGroupEntity;

public final class EmployeeGroupMapper {

    public static EmployeeGroupDTO employeeGroupDTO(EmployeeGroupEntity entity) {
        return new EmployeeGroupDTO(
                entity.getId(),
                entity.getName(),
                entity.getActive()
        );
    }

    public static EmployeeGroupEntity employeeGroupEntity(CreateEmployeeGroupRequest request) {
        EmployeeGroupEntity entity = applyRequest(new EmployeeGroupEntity(), request);
        entity.setActive(true);
        return entity;
    }

    public static EmployeeGroupEntity employeeGroupEntity(EmployeeGroupEntity entity, CreateEmployeeGroupRequest request) {
        return applyRequest(entity, request);
    }

    public static String resolveCode( String name) {
        return name.trim().replaceAll("[\\s-]+", "_").toUpperCase();
    }

    private static EmployeeGroupEntity applyRequest(EmployeeGroupEntity entity, CreateEmployeeGroupRequest request) {
        entity.setName(request.name());
        entity.setAlias(resolveCode(request.name()));
        return entity;
    }
}
