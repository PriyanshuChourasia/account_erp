package com.codymitra.shared_service.modules.department.services;

import com.codymitra.shared_service.modules.department.dtos.CreateDepartmentRequest;
import com.codymitra.shared_service.modules.department.dtos.DepartmentDTO;
import com.codymitra.shared_service.modules.department.dtos.DepartmentHierarchyDTO;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDTO> getAll();
    List<DepartmentHierarchyDTO> getAllWithChildren();
    String create(CreateDepartmentRequest request);
}
