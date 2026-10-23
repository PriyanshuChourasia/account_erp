package com.codymitra.shared_service.modules.department.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.department.dtos.CreateDepartmentRequest;
import com.codymitra.shared_service.modules.department.dtos.DepartmentDTO;
import com.codymitra.shared_service.modules.department.dtos.DepartmentHierarchyDTO;
import com.codymitra.shared_service.modules.department.entities.DepartmentEntity;
import com.codymitra.shared_service.modules.department.mappers.DepartmentMapper;
import com.codymitra.shared_service.modules.department.repositories.DepartmentRepository;
import com.codymitra.shared_service.modules.department.services.DepartmentService;
import com.codymitra.shared_service.modules.department.utils.DepartmentHierarchy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDTO> getAll() {
        return departmentRepository.findAll().stream().map(DepartmentMapper::departmentDTO).toList();
    }

    @Override
    public List<DepartmentHierarchyDTO> getAllWithChildren() {
        return DepartmentHierarchy.departmentDTO(departmentRepository.findAll());
    }

    @Override
    public String create(CreateDepartmentRequest request) {
        if (departmentRepository.existsByName(request.name())) {
            throw new DataAlreadyExistsException("Department already exists with this name");
        }
        DepartmentEntity department = DepartmentMapper.departmentEntity(request);
        departmentRepository.save(department);
        return "Department created successfully";
    }

    @Override
    @Transactional
    public DepartmentDTO update(UUID id, CreateDepartmentRequest request) {
        DepartmentEntity department = findById(id);

        if (departmentRepository.existsByNameAndIdNot(request.name(), id)) {
            throw new DataAlreadyExistsException("Department already exists with this name");
        }
        if (request.code() != null && departmentRepository.existsByCodeAndIdNot(request.code().toUpperCase(), id)) {
            throw new DataAlreadyExistsException("Department already exists with this code");
        }

        DepartmentMapper.departmentEntity(department, request);
        return DepartmentMapper.departmentDTO(departmentRepository.save(department));
    }

    private DepartmentEntity findById(UUID id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such department found with this id")
        );
    }

    private void validateParent(UUID parentId, UUID id) {
        if (parentId == null) {
            return;
        }
        if (parentId.equals(id)) {
            throw new IllegalArgumentException("Department cannot be its own parent");
        }

        // walk up from the new parent; reaching this department would create a cycle
        UUID currentId = parentId;
        while (currentId != null) {
            DepartmentEntity current = departmentRepository.findById(currentId).orElseThrow(
                    () -> new DataNotFoundException("Parent department does not exist")
            );
            if (current.getId().equals(id)) {
                throw new IllegalArgumentException("Department cannot be moved under its own child");
            }
            currentId = current.getParentId();
        }
    }
}
