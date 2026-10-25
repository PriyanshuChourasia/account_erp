package com.codymitra.shared_service.modules.employee_employment_detail.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.employee.entities.EmployeeEntity;
import com.codymitra.shared_service.modules.employee.repositories.EmployeeRepository;
import com.codymitra.shared_service.modules.employee_employment_detail.dtos.CreateEmployeeEmploymentDetailRequest;
import com.codymitra.shared_service.modules.employee_employment_detail.dtos.EmployeeEmploymentDetailDTO;
import com.codymitra.shared_service.modules.employee_employment_detail.entities.EmployeeEmploymentDetailEntity;
import com.codymitra.shared_service.modules.employee_employment_detail.mappers.EmployeeEmploymentDetailMapper;
import com.codymitra.shared_service.modules.employee_employment_detail.repositories.EmployeeEmploymentDetailRepository;
import com.codymitra.shared_service.modules.employee_employment_detail.services.EmployeeEmploymentDetailService;
import com.codymitra.shared_service.modules.employment_type.entities.EmploymentTypeEntity;
import com.codymitra.shared_service.modules.employment_type.repositories.EmploymentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeEmploymentDetailServiceImpl implements EmployeeEmploymentDetailService {

    private final EmployeeEmploymentDetailRepository employeeEmploymentDetailRepository;
    private final EmployeeRepository employeeRepository;
    private final EmploymentTypeRepository employmentTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeEmploymentDetailDTO> getAll() {
        return employeeEmploymentDetailRepository.findAll().stream()
                .map(EmployeeEmploymentDetailMapper::employeeEmploymentDetailDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeEmploymentDetailDTO getById(UUID id) {
        return EmployeeEmploymentDetailMapper.employeeEmploymentDetailDTO(findById(id));
    }

    @Override
    @Transactional
    public EmployeeEmploymentDetailDTO create(CreateEmployeeEmploymentDetailRequest request) {
        validateDates(request);
        EmployeeEntity employee = findEmployee(request.employeeId());
        EmploymentTypeEntity employmentType = findEmploymentType(request.employmentTypeId());
        return EmployeeEmploymentDetailMapper.employeeEmploymentDetailDTO(
                employeeEmploymentDetailRepository.save(
                        EmployeeEmploymentDetailMapper.employeeEmploymentDetailEntity(request, employee, employmentType)));
    }

    @Override
    @Transactional
    public EmployeeEmploymentDetailDTO update(UUID id, CreateEmployeeEmploymentDetailRequest request) {
        EmployeeEmploymentDetailEntity entity = findById(id);
        validateDates(request);
        EmployeeEntity employee = findEmployee(request.employeeId());
        EmploymentTypeEntity employmentType = findEmploymentType(request.employmentTypeId());
        return EmployeeEmploymentDetailMapper.employeeEmploymentDetailDTO(
                employeeEmploymentDetailRepository.save(
                        EmployeeEmploymentDetailMapper.employeeEmploymentDetailEntity(entity, request, employee, employmentType)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        employeeEmploymentDetailRepository.delete(findById(id));
        return "Employee employment detail deleted successfully";
    }

    private EmployeeEmploymentDetailEntity findById(UUID id) {
        return employeeEmploymentDetailRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such employee employment detail found with this id")
        );
    }

    private EmployeeEntity findEmployee(UUID employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(
                () -> new DataNotFoundException("No such employee found with this id")
        );
    }

    private EmploymentTypeEntity findEmploymentType(UUID employmentTypeId) {
        return employmentTypeRepository.findById(employmentTypeId).orElseThrow(
                () -> new DataNotFoundException("No such employment type found with this id")
        );
    }

    private void validateDates(CreateEmployeeEmploymentDetailRequest request) {
        if (request.dateOfExit() != null && request.dateOfExit().isBefore(request.dateOfJoining())) {
            throw new IllegalArgumentException("Date of exit cannot be before date of joining");
        }
    }
}
