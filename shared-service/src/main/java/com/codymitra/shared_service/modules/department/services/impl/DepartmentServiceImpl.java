package com.codymitra.shared_service.modules.department.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
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

import java.util.List;

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
}
