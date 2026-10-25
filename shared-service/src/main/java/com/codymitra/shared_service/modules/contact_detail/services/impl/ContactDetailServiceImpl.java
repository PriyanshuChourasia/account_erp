package com.codymitra.shared_service.modules.contact_detail.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.contact_detail.dtos.ContactDetailDTO;
import com.codymitra.shared_service.modules.contact_detail.dtos.CreateContactDetailRequest;
import com.codymitra.shared_service.modules.contact_detail.entities.ContactDetailEntity;
import com.codymitra.shared_service.modules.contact_detail.mappers.ContactDetailMapper;
import com.codymitra.shared_service.modules.contact_detail.repositories.ContactDetailRepository;
import com.codymitra.shared_service.modules.contact_detail.services.ContactDetailService;
import com.codymitra.shared_service.modules.employee.entities.EmployeeEntity;
import com.codymitra.shared_service.modules.employee.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactDetailServiceImpl implements ContactDetailService {

    private final ContactDetailRepository contactDetailRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ContactDetailDTO> getAll() {
        return contactDetailRepository.findAll().stream()
                .map(ContactDetailMapper::contactDetailDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ContactDetailDTO getById(UUID id) {
        return ContactDetailMapper.contactDetailDTO(findById(id));
    }

    @Override
    @Transactional
    public ContactDetailDTO create(CreateContactDetailRequest request) {
        EmployeeEntity employee = findEmployee(request.employeeId());
        return ContactDetailMapper.contactDetailDTO(
                contactDetailRepository.save(ContactDetailMapper.contactDetailEntity(request, employee)));
    }

    @Override
    @Transactional
    public ContactDetailDTO update(UUID id, CreateContactDetailRequest request) {
        ContactDetailEntity entity = findById(id);
        EmployeeEntity employee = findEmployee(request.employeeId());
        return ContactDetailMapper.contactDetailDTO(
                contactDetailRepository.save(ContactDetailMapper.contactDetailEntity(entity, request, employee)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        contactDetailRepository.delete(findById(id));
        return "Contact detail deleted successfully";
    }

    private ContactDetailEntity findById(UUID id) {
        return contactDetailRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such contact detail found with this id")
        );
    }

    private EmployeeEntity findEmployee(UUID employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(
                () -> new DataNotFoundException("No such employee found with this id")
        );
    }
}