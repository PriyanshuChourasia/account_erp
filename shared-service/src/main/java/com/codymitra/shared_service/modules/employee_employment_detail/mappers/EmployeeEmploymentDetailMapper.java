package com.codymitra.shared_service.modules.employee_employment_detail.mappers;

import com.codymitra.shared_service.modules.employee.entities.EmployeeEntity;
import com.codymitra.shared_service.modules.employee_employment_detail.dtos.CreateEmployeeEmploymentDetailRequest;
import com.codymitra.shared_service.modules.employee_employment_detail.dtos.EmployeeEmploymentDetailDTO;
import com.codymitra.shared_service.modules.employee_employment_detail.entities.EmployeeEmploymentDetailEntity;
import com.codymitra.shared_service.modules.employment_type.entities.EmploymentTypeEntity;

public final class EmployeeEmploymentDetailMapper {

    public static EmployeeEmploymentDetailDTO employeeEmploymentDetailDTO(EmployeeEmploymentDetailEntity entity) {
        return new EmployeeEmploymentDetailDTO(
                entity.getId(),
                entity.getEmployee() != null ? entity.getEmployee().getId() : null,
                entity.getEmployee() != null ? entity.getEmployee().getEmployeeCode() : null,
                entity.getEmploymentType() != null ? entity.getEmploymentType().getId() : null,
                entity.getEmploymentType() != null ? entity.getEmploymentType().getName() : null,
                entity.getDateOfJoining(),
                entity.getDateOfExit(),
                entity.getStatus(),
                entity.getActive()
        );
    }

    public static EmployeeEmploymentDetailEntity employeeEmploymentDetailEntity(
            CreateEmployeeEmploymentDetailRequest request,
            EmployeeEntity employee,
            EmploymentTypeEntity employmentType) {
        EmployeeEmploymentDetailEntity entity = applyRequest(new EmployeeEmploymentDetailEntity(), request, employee, employmentType);
        entity.setActive(true);
        return entity;
    }

    public static EmployeeEmploymentDetailEntity employeeEmploymentDetailEntity(
            EmployeeEmploymentDetailEntity entity,
            CreateEmployeeEmploymentDetailRequest request,
            EmployeeEntity employee,
            EmploymentTypeEntity employmentType) {
        return applyRequest(entity, request, employee, employmentType);
    }

    private static EmployeeEmploymentDetailEntity applyRequest(
            EmployeeEmploymentDetailEntity entity,
            CreateEmployeeEmploymentDetailRequest request,
            EmployeeEntity employee,
            EmploymentTypeEntity employmentType) {
        entity.setEmployee(employee);
        entity.setEmploymentType(employmentType);
        entity.setDateOfJoining(request.dateOfJoining());
        entity.setDateOfExit(request.dateOfExit());
        entity.setStatus(request.status());
        return entity;
    }
}
