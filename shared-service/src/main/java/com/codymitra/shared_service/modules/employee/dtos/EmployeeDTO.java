package com.codymitra.shared_service.modules.employee.dtos;

import com.codymitra.shared_service.modules.employee.enums.EmployeeStatusEnum;
import com.codymitra.shared_service.modules.employee.enums.EmploymentTypeEnum;
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
        String companyMail,
        String contactNo,
        String altContactNo,
        UUID bloodGroupId,
        String bloodGroupName,
        LocalDate dateOfJoining,
        LocalDate dateOfExit,
        EmploymentTypeEnum employmentType,
        EmployeeStatusEnum status,
        UUID companyId,
        String companyName,
        UUID departmentId,
        String departmentName,
        UUID employeeGroupId,
        String employeeGroupName,
        UUID designationId,
        String designationName,
        String pfNo,
        String esiNo,
        String uanNo,
        Boolean active
) {
}
