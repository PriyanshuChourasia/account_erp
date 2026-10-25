package com.codymitra.shared_service.modules.contact_detail.mappers;

import com.codymitra.shared_service.modules.contact_detail.dtos.ContactDetailDTO;
import com.codymitra.shared_service.modules.contact_detail.dtos.CreateContactDetailRequest;
import com.codymitra.shared_service.modules.contact_detail.entities.ContactDetailEntity;
import com.codymitra.shared_service.modules.employee.entities.EmployeeEntity;

public final class ContactDetailMapper {

    public static ContactDetailDTO contactDetailDTO(ContactDetailEntity entity) {
        return new ContactDetailDTO(
                entity.getId(),
                entity.getEmployee() != null ? entity.getEmployee().getId() : null,
                entity.getEmployee() != null ? entity.getEmployee().getEmployeeCode() : null,
                entity.getMobile(),
                entity.getAltMobile(),
                entity.getEmail(),
                entity.getPersonalEmail(),
                entity.getAddress(),
                entity.getEmergencyContactName(),
                entity.getEmergencyContactNumber(),
                entity.getActive()
        );
    }

    public static ContactDetailEntity contactDetailEntity(CreateContactDetailRequest request, EmployeeEntity employee) {
        ContactDetailEntity entity = applyRequest(new ContactDetailEntity(), request, employee);
        entity.setActive(true);
        return entity;
    }

    public static ContactDetailEntity contactDetailEntity(ContactDetailEntity entity, CreateContactDetailRequest request, EmployeeEntity employee) {
        return applyRequest(entity, request, employee);
    }

    public static String resolveOptional(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }

    private static ContactDetailEntity applyRequest(ContactDetailEntity entity, CreateContactDetailRequest request, EmployeeEntity employee) {
        entity.setEmployee(employee);
        entity.setMobile(resolveOptional(request.mobile()));
        entity.setAltMobile(resolveOptional(request.altMobile()));
        entity.setEmail(resolveOptional(request.email()));
        entity.setPersonalEmail(resolveOptional(request.personalEmail()));
        entity.setAddress(resolveOptional(request.address()));
        entity.setEmergencyContactName(resolveOptional(request.emergencyContactName()));
        entity.setEmergencyContactNumber(resolveOptional(request.emergencyContactNumber()));
        return entity;
    }
}