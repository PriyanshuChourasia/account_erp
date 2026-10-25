package com.codymitra.shared_service.modules.employee.mappers;

import com.codymitra.shared_service.modules.blood_group.entities.BloodGroupEntity;
import com.codymitra.shared_service.modules.company.entities.CompanyEntity;
import com.codymitra.shared_service.modules.department.entities.DepartmentEntity;
import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;
import com.codymitra.shared_service.modules.employee.dtos.CreateEmployeeRequest;
import com.codymitra.shared_service.modules.employee.dtos.EmployeeDTO;
import com.codymitra.shared_service.modules.employee.entities.EmployeeEntity;
import com.codymitra.shared_service.modules.employee.enums.EmployeeStatusEnum;
import com.codymitra.shared_service.modules.employee_group.entities.EmployeeGroupEntity;

public final class EmployeeMapper {

    public static EmployeeDTO employeeDTO(EmployeeEntity entity) {
        return new EmployeeDTO(
                entity.getId(),
                entity.getEmployeeCode(),
                entity.getFirstName(),
                entity.getMiddleName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getCompanyMail(),
                entity.getContactNo(),
                entity.getAltContactNo(),
                entity.getBloodGroup() != null ? entity.getBloodGroup().getId() : null,
                entity.getBloodGroup() != null ? entity.getBloodGroup().getName() : null,
                entity.getDateOfJoining(),
                entity.getDateOfExit(),
                entity.getEmploymentType(),
                entity.getStatus(),
                entity.getCompany() != null ? entity.getCompany().getId() : null,
                entity.getCompany() != null ? entity.getCompany().getName() : null,
                entity.getDepartment() != null ? entity.getDepartment().getId() : null,
                entity.getDepartment() != null ? entity.getDepartment().getName() : null,
                entity.getEmployeeGroup() != null ? entity.getEmployeeGroup().getId() : null,
                entity.getEmployeeGroup() != null ? entity.getEmployeeGroup().getName() : null,
                entity.getDesignation() != null ? entity.getDesignation().getId() : null,
                entity.getDesignation() != null ? entity.getDesignation().getName() : null,
                entity.getPfNo(),
                entity.getEsiNo(),
                entity.getUanNo(),
                entity.getActive()
        );
    }

    public static EmployeeEntity employeeEntity(CreateEmployeeRequest request, String employeeCode, EmployeeRelations relations) {
        EmployeeEntity entity = applyRequest(new EmployeeEntity(), request, employeeCode, relations);
        entity.setActive(true);
        return entity;
    }

    public static EmployeeEntity employeeEntity(EmployeeEntity entity, CreateEmployeeRequest request, String employeeCode, EmployeeRelations relations) {
        return applyRequest(entity, request, employeeCode, relations);
    }

    public static String resolveEmployeeCode(String employeeCode, String firstName, String lastName) {
        if (employeeCode != null && !employeeCode.isBlank()) {
            return employeeCode.trim().toUpperCase();
        }
        String name = (firstName + " " + (lastName != null ? lastName : "")).trim();
        return "EMP_" + name.replaceAll("[\\s-]+", "_").toUpperCase();
    }

    // optional unique values; blank is stored as null so the unique constraint allows many empty values
    public static String resolveMail(String mail) {
        return mail == null || mail.isBlank() ? null : mail.trim().toLowerCase();
    }

    private static EmployeeEntity applyRequest(EmployeeEntity entity, CreateEmployeeRequest request, String employeeCode, EmployeeRelations relations) {
        entity.setEmployeeCode(employeeCode);
        entity.setFirstName(request.firstName());
        entity.setMiddleName(request.middleName());
        entity.setLastName(request.lastName());
        entity.setEmail(resolveMail(request.email()));
        entity.setCompanyMail(resolveMail(request.companyMail()));
        entity.setContactNo(request.contactNo());
        entity.setAltContactNo(request.altContactNo());
        entity.setBloodGroup(relations.bloodGroup());
        entity.setDateOfJoining(request.dateOfJoining());
        entity.setDateOfExit(request.dateOfExit());
        entity.setEmploymentType(request.employmentType());
        if (request.status() != null) {
            entity.setStatus(request.status());
        } else if (entity.getStatus() == null) {
            entity.setStatus(EmployeeStatusEnum.ACTIVE);
        }
        entity.setCompany(relations.company());
        entity.setDepartment(relations.department());
        entity.setEmployeeGroup(relations.employeeGroup());
        entity.setDesignation(relations.designation());
        entity.setPfNo(request.pfNo());
        entity.setEsiNo(request.esiNo());
        entity.setUanNo(request.uanNo());
        return entity;
    }

    public record EmployeeRelations(
            BloodGroupEntity bloodGroup,
            CompanyEntity company,
            DepartmentEntity department,
            EmployeeGroupEntity employeeGroup,
            DesignationEntity designation
    ) {
    }
}
