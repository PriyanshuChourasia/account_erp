package com.codymitra.shared_service.modules.employee_employment_detail.dtos;

import com.codymitra.shared_service.modules.employee.enums.EmployeeStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmployeeEmploymentDetailDTO(
        UUID id,
        UUID employeeId,
        String employeeCode,
        UUID employmentTypeId,
        String employmentTypeName,
        LocalDate dateOfJoining,
        LocalDate dateOfExit,
        EmployeeStatusEnum status,
        Boolean active
) {
}
