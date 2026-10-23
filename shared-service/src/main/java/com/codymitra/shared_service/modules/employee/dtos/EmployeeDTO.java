package com.codymitra.shared_service.modules.employee.dtos;

import com.codymitra.shared_service.modules.employee.enums.EmployeeStatusEnum;
import com.codymitra.shared_service.modules.employee.enums.EmploymentTypeEnum;
import com.codymitra.shared_service.modules.employee.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmployeeDTO(
        UUID id,
        String employeeCode,
        String firstName,
        String middleName,
        String lastName,
        String email,
        String contactNo,
        String altContactNo,
        LocalDate dateOfBirth,
        GenderEnum gender,
        String bloodGroup,
        LocalDate dateOfJoining,
        LocalDate dateOfExit,
        EmploymentTypeEnum employmentType,
        EmployeeStatusEnum status,
        UUID companyId,
        String companyName,
        UUID departmentId,
        String departmentName,
        UUID designationId,
        String designationName,
        UUID reportingManagerId,
        String reportingManagerName,
        String panNo,
        String aadhaarNo,
        String pfNo,
        String esiNo,
        String uanNo,
        Boolean active
) {
}
