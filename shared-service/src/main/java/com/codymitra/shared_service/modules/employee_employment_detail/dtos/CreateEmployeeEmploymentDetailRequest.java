package com.codymitra.shared_service.modules.employee_employment_detail.dtos;

import com.codymitra.shared_service.modules.employee.enums.EmployeeStatusEnum;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CreateEmployeeEmploymentDetailRequest(
        @NotNull(message = "Employee id is required")
        UUID employeeId,

        @NotNull(message = "Employment type id is required")
        UUID employmentTypeId,

        @NotNull(message = "Date of joining is required")
        LocalDate dateOfJoining,

        LocalDate dateOfExit,

        @NotNull(message = "Status is required")
        EmployeeStatusEnum status
) {
}
