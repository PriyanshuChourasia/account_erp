package com.codymitra.shared_service.modules.employee_work_unit.mappers;

import com.codymitra.shared_service.modules.employee_work_unit.dtos.CreateEmployeeWorkUnitRequest;
import com.codymitra.shared_service.modules.employee_work_unit.dtos.EmployeeWorkUnitDTO;
import com.codymitra.shared_service.modules.employee_work_unit.entities.EmployeeWorkUnitEntity;

public final class EmployeeWorkUnitMapper {

    public static EmployeeWorkUnitDTO employeeWorkUnitDTO(EmployeeWorkUnitEntity entity) {
        return new EmployeeWorkUnitDTO(
                entity.getId(),
                entity.getUnitType(),
                entity.getName(),
                entity.getSymbol(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static EmployeeWorkUnitEntity employeeWorkUnitEntity(CreateEmployeeWorkUnitRequest request) {
        EmployeeWorkUnitEntity entity = applyRequest(new EmployeeWorkUnitEntity(), request);
        entity.setActive(true);
        return entity;
    }

    public static EmployeeWorkUnitEntity employeeWorkUnitEntity(EmployeeWorkUnitEntity entity, CreateEmployeeWorkUnitRequest request) {
        return applyRequest(entity, request);
    }

    public static String resolveSymbol(String symbol) {
        return symbol == null || symbol.isBlank() ? null : symbol.trim();
    }

    private static EmployeeWorkUnitEntity applyRequest(EmployeeWorkUnitEntity entity, CreateEmployeeWorkUnitRequest request) {
        entity.setName(request.name().trim());
        entity.setUnitType(request.unitType());
        entity.setSymbol(resolveSymbol(request.symbol()));
        entity.setDescription(request.description());
        return entity;
    }
}
