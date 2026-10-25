package com.codymitra.shared_service.modules.employee.dtos;

import com.codymitra.shared_service.modules.employee.enums.EmployeeStatusEnum;
import com.codymitra.shared_service.modules.employee.enums.EmploymentTypeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

public record CreateEmployeeRequest(
        String employeeCode,

        @NotBlank(message = "First name is required")
        String firstName,

        String middleName,

        String lastName,

        @Email(message = "Invalid email")
        String email,

        @Email(message = "Invalid company mail")
        String companyMail,

        String contactNo,

        String altContactNo,

        UUID bloodGroupId,

        LocalDate dateOfJoining,

        LocalDate dateOfExit,

        EmploymentTypeEnum employmentType,

        EmployeeStatusEnum status,

        UUID companyId,

        UUID departmentId,

        UUID employeeGroupId,

        UUID designationId,

        String pfNo,

        String esiNo,

        String uanNo
) {
}
