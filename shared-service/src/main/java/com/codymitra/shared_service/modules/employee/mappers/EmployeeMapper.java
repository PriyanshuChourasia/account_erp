package com.codymitra.shared_service.modules.employee.mappers;

import com.codymitra.shared_service.modules.company.entities.CompanyEntity;
import com.codymitra.shared_service.modules.department.entities.DepartmentEntity;
import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;
import com.codymitra.shared_service.modules.employee.dtos.CreateEmployeeRequest;
import com.codymitra.shared_service.modules.employee.dtos.EmployeeDTO;
import com.codymitra.shared_service.modules.employee.entities.EmployeeEntity;
import com.codymitra.shared_service.modules.employee.enums.EmployeeStatusEnum;

public final class EmployeeMapper {

    public static EmployeeDTO employeeDTO(EmployeeEntity entity) {
        return new EmployeeDTO(
                entity.getId(),
                entity.getEmployeeCode(),
                entity.getFirstName(),
                entity.getMiddleName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getContactNo(),
                entity.getAltContactNo(),
                entity.getDateOfBirth(),
                entity.getGender(),
                entity.getBloodGroup(),
                entity.getDateOfJoining(),
                entity.getDateOfExit(),
                entity.getEmploymentType(),
                entity.getStatus(),
                entity.getCompany() != null ? entity.getCompany().getId() : null,
                entity.getCompany() != null ? entity.getCompany().getName() : null,
                entity.getDepartment() != null ? entity.getDepartment().getId() : null,
                entity.getDepartment() != null ? entity.getDepartment().getName() : null,
                entity.getDesignation() != null ? entity.getDesignation().getId() : null,
                entity.getDesignation() != null ? entity.getDesignation().getName() : null,
                entity.getReportingManager() != null ? entity.getReportingManager().getId() : null,
                entity.getReportingManager() != null ? employeeName(entity.getReportingManager()) : null,
                entity.getPanNo(),
                entity.getAadhaarNo(),
                entity.getPfNo(),
                entity.getEsiNo(),
                entity.getUanNo(),
                entity.getActive()
        );
    }

    public static EmployeeEntity employeeEntity(CreateEmployeeRequest request, String employeeCode,
                                                CompanyEntity company, DepartmentEntity department,
                                                DesignationEntity designation, EmployeeEntity reportingManager) {
        EmployeeEntity entity = applyRequest(new EmployeeEntity(), request, employeeCode, company, department, designation, reportingManager);
        entity.setActive(true);
        return entity;
    }

    public static EmployeeEntity employeeEntity(EmployeeEntity entity, CreateEmployeeRequest request, String employeeCode,
                                                CompanyEntity company, DepartmentEntity department,
                                                DesignationEntity designation, EmployeeEntity reportingManager) {
        return applyRequest(entity, request, employeeCode, company, department, designation, reportingManager);
    }

    public static String resolveEmployeeCode(String employeeCode, String firstName, String lastName) {
        if (employeeCode != null && !employeeCode.isBlank()) {
            return employeeCode.trim().toUpperCase();
        }
        String name = (firstName + " " + (lastName != null ? lastName : "")).trim();
        return "EMP_" + name.replaceAll("[\\s-]+", "_").toUpperCase();
    }

    private static EmployeeEntity applyRequest(EmployeeEntity entity, CreateEmployeeRequest request, String employeeCode,
                                               CompanyEntity company, DepartmentEntity department,
                                               DesignationEntity designation, EmployeeEntity reportingManager) {
        entity.setEmployeeCode(employeeCode);
        entity.setFirstName(request.firstName());
        entity.setMiddleName(request.middleName());
        entity.setLastName(request.lastName());
        entity.setEmail(request.email());
        entity.setContactNo(request.contactNo());
        entity.setAltContactNo(request.altContactNo());
        entity.setDateOfBirth(request.dateOfBirth());
        entity.setGender(request.gender());
        entity.setBloodGroup(request.bloodGroup());
        entity.setDateOfJoining(request.dateOfJoining());
        entity.setDateOfExit(request.dateOfExit());
        entity.setEmploymentType(request.employmentType());
        if (request.status() != null) {
            entity.setStatus(request.status());
        } else if (entity.getStatus() == null) {
            entity.setStatus(EmployeeStatusEnum.ACTIVE);
        }
        entity.setCompany(company);
        entity.setDepartment(department);
        entity.setDesignation(designation);
        entity.setReportingManager(reportingManager);
        entity.setPanNo(request.panNo());
        entity.setAadhaarNo(request.aadhaarNo());
        entity.setPfNo(request.pfNo());
        entity.setEsiNo(request.esiNo());
        entity.setUanNo(request.uanNo());
        return entity;
    }

    private static String employeeName(EmployeeEntity entity) {
        String name = entity.getFirstName();
        if (entity.getMiddleName() != null && !entity.getMiddleName().isBlank()) {
            name = name + " " + entity.getMiddleName();
        }
        if (entity.getLastName() != null && !entity.getLastName().isBlank()) {
            name = name + " " + entity.getLastName();
        }
        return name;
    }
}
