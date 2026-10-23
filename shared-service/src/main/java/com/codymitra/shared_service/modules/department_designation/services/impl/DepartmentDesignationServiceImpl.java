package com.codymitra.shared_service.modules.department_designation.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.department.entities.DepartmentEntity;
import com.codymitra.shared_service.modules.department.repositories.DepartmentRepository;
import com.codymitra.shared_service.modules.department_designation.dtos.CreateDepartmentDesignationRequest;
import com.codymitra.shared_service.modules.department_designation.dtos.DepartmentDesignationDTO;
import com.codymitra.shared_service.modules.department_designation.entities.DepartmentDesignationEntity;
import com.codymitra.shared_service.modules.department_designation.mappers.DepartmentDesignationMapper;
import com.codymitra.shared_service.modules.department_designation.repositories.DepartmentDesignationRepository;
import com.codymitra.shared_service.modules.department_designation.services.DepartmentDesignationService;
import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;
import com.codymitra.shared_service.modules.designation.repositories.DesignationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentDesignationServiceImpl implements DepartmentDesignationService {

    private final DepartmentDesignationRepository departmentDesignationRepository;
    private final DepartmentRepository departmentRepository;
    private final DesignationRepository designationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDesignationDTO> getAll() {
        return departmentDesignationRepository.findAll().stream()
                .map(DepartmentDesignationMapper::departmentDesignationDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDesignationDTO getById(UUID id) {
        return DepartmentDesignationMapper.departmentDesignationDTO(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDesignationDTO> getByDepartmentId(UUID departmentId) {
        return departmentDesignationRepository.findByDepartment_Id(departmentId).stream()
                .map(DepartmentDesignationMapper::departmentDesignationDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDesignationDTO> getByDesignationId(UUID designationId) {
        return departmentDesignationRepository.findByDesignation_Id(designationId).stream()
                .map(DepartmentDesignationMapper::departmentDesignationDTO)
                .toList();
    }

    @Override
    @Transactional
    public DepartmentDesignationDTO create(CreateDepartmentDesignationRequest request) {
        DepartmentEntity department = findDepartment(request.departmentId());
        DesignationEntity designation = findDesignation(request.designationId());
        if (departmentDesignationRepository.existsByDepartment_IdAndDesignation_Id(request.departmentId(), request.designationId())) {
            throw new DataAlreadyExistsException("Designation is already assigned to this department");
        }
        DepartmentDesignationEntity saved = departmentDesignationRepository.save(
                DepartmentDesignationMapper.departmentDesignationEntity(request, department, designation)
        );
        return DepartmentDesignationMapper.departmentDesignationDTO(saved);
    }

    @Override
    @Transactional
    public DepartmentDesignationDTO update(UUID id, CreateDepartmentDesignationRequest request) {
        DepartmentDesignationEntity entity = findById(id);
        DepartmentEntity department = findDepartment(request.departmentId());
        DesignationEntity designation = findDesignation(request.designationId());
        if (departmentDesignationRepository.existsByDepartment_IdAndDesignation_IdAndIdNot(
                request.departmentId(), request.designationId(), id)) {
            throw new DataAlreadyExistsException("Designation is already assigned to this department");
        }
        DepartmentDesignationEntity saved = departmentDesignationRepository.save(
                DepartmentDesignationMapper.departmentDesignationEntity(entity, request, department, designation)
        );
        return DepartmentDesignationMapper.departmentDesignationDTO(saved);
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        departmentDesignationRepository.delete(findById(id));
        return "Designation removed from department successfully";
    }

    private DepartmentDesignationEntity findById(UUID id) {
        return departmentDesignationRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Department designation does not exist")
        );
    }

    private DepartmentEntity findDepartment(UUID departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(
                () -> new DataNotFoundException("No such department exists")
        );
    }

    private DesignationEntity findDesignation(UUID designationId) {
        return designationRepository.findById(designationId).orElseThrow(
                () -> new DataNotFoundException("No such designation exists")
        );
    }
}
