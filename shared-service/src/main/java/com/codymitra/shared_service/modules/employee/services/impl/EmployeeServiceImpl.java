package com.codymitra.shared_service.modules.employee.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.company.entities.CompanyEntity;
import com.codymitra.shared_service.modules.company.repositories.CompanyRepository;
import com.codymitra.shared_service.modules.department.entities.DepartmentEntity;
import com.codymitra.shared_service.modules.department.repositories.DepartmentRepository;
import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;
import com.codymitra.shared_service.modules.designation.repositories.DesignationRepository;
import com.codymitra.shared_service.modules.employee.dtos.CreateEmployeeRequest;
import com.codymitra.shared_service.modules.employee.dtos.EmployeeDTO;
import com.codymitra.shared_service.modules.employee.entities.EmployeeEntity;
import com.codymitra.shared_service.modules.employee.mappers.EmployeeMapper;
import com.codymitra.shared_service.modules.employee.repositories.EmployeeRepository;
import com.codymitra.shared_service.modules.employee.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository;
    private final DesignationRepository designationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::employeeDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO getById(UUID id) {
        return EmployeeMapper.employeeDTO(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getByDepartment(UUID departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new DataNotFoundException("No such department found with this id");
        }
        return employeeRepository.findByDepartmentId(departmentId).stream()
                .map(EmployeeMapper::employeeDTO)
                .toList();
    }

    @Override
    @Transactional
    public EmployeeDTO create(CreateEmployeeRequest request) {
        String employeeCode = EmployeeMapper.resolveEmployeeCode(request.employeeCode(), request.firstName(), request.lastName());
        validateUnique(employeeCode, request.email(), null);
        validateDates(request.dateOfJoining(), request.dateOfExit());

        EmployeeEntity entity = EmployeeMapper.employeeEntity(
                request,
                employeeCode,
                resolveCompany(request.companyId()),
                resolveDepartment(request.departmentId()),
                resolveDesignation(request.designationId()),
                resolveReportingManager(request.reportingManagerId(), null)
        );

        return EmployeeMapper.employeeDTO(employeeRepository.save(entity));
    }

    @Override
    @Transactional
    public EmployeeDTO update(UUID id, CreateEmployeeRequest request) {
        EmployeeEntity entity = findById(id);
        String employeeCode = EmployeeMapper.resolveEmployeeCode(request.employeeCode(), request.firstName(), request.lastName());
        validateUnique(employeeCode, request.email(), id);
        validateDates(request.dateOfJoining(), request.dateOfExit());

        EmployeeEntity updated = EmployeeMapper.employeeEntity(
                entity,
                request,
                employeeCode,
                resolveCompany(request.companyId()),
                resolveDepartment(request.departmentId()),
                resolveDesignation(request.designationId()),
                resolveReportingManager(request.reportingManagerId(), id)
        );

        return EmployeeMapper.employeeDTO(employeeRepository.save(updated));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        employeeRepository.delete(findById(id));
        return "Employee deleted successfully";
    }

    private EmployeeEntity findById(UUID id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such employee found with this id")
        );
    }

    private void validateUnique(String employeeCode, String email, UUID id) {
        if (id == null) {
            if (employeeRepository.existsByEmployeeCode(employeeCode)) {
                throw new DataAlreadyExistsException("Employee already exists with this code");
            }
            if (email != null && !email.isBlank() && employeeRepository.existsByEmail(email)) {
                throw new DataAlreadyExistsException("Employee already exists with this email");
            }
            return;
        }

        if (employeeRepository.existsByEmployeeCodeAndIdNot(employeeCode, id)) {
            throw new DataAlreadyExistsException("Employee already exists with this code");
        }
        if (email != null && !email.isBlank() && employeeRepository.existsByEmailAndIdNot(email, id)) {
            throw new DataAlreadyExistsException("Employee already exists with this email");
        }
    }

    private void validateDates(LocalDate dateOfJoining, LocalDate dateOfExit) {
        if (dateOfJoining != null && dateOfExit != null && dateOfExit.isBefore(dateOfJoining)) {
            throw new IllegalArgumentException("Date of exit cannot be before date of joining");
        }
    }

    private CompanyEntity resolveCompany(UUID companyId) {
        if (companyId == null) {
            return null;
        }
        return companyRepository.findById(companyId).orElseThrow(
                () -> new DataNotFoundException("No such company found with this id")
        );
    }

    private DepartmentEntity resolveDepartment(UUID departmentId) {
        if (departmentId == null) {
            return null;
        }
        return departmentRepository.findById(departmentId).orElseThrow(
                () -> new DataNotFoundException("No such department found with this id")
        );
    }

    private DesignationEntity resolveDesignation(UUID designationId) {
        if (designationId == null) {
            return null;
        }
        return designationRepository.findById(designationId).orElseThrow(
                () -> new DataNotFoundException("No such designation found with this id")
        );
    }

    private EmployeeEntity resolveReportingManager(UUID reportingManagerId, UUID employeeId) {
        if (reportingManagerId == null) {
            return null;
        }
        if (reportingManagerId.equals(employeeId)) {
            throw new IllegalArgumentException("Employee cannot be their own reporting manager");
        }
        return employeeRepository.findById(reportingManagerId).orElseThrow(
                () -> new DataNotFoundException("No such reporting manager found with this id")
        );
    }
}
