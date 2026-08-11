package com.codymitra.shared_service.modules.department.utils;

import com.codymitra.shared_service.modules.department.dtos.DepartmentHierarchyDTO;
import com.codymitra.shared_service.modules.department.entities.DepartmentEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DepartmentHierarchy {

    public static List<DepartmentHierarchyDTO> departmentDTO(List<DepartmentEntity> departmentEntities) {

        Map<Long, DepartmentHierarchyDTO> departmentDTOMap = new HashMap<>();

        for (DepartmentEntity department : departmentEntities) {
            departmentDTOMap.put(department.getId(),
                    new DepartmentHierarchyDTO(
                            department.getId(),
                            department.getName(),
                            department.getCode(),
                            department.getParentId(),
                            department.getDescription(),
                            department.getActive(),
                            new ArrayList<>()
                    ));
        }

        List<DepartmentHierarchyDTO> roots = new ArrayList<>();

        for (DepartmentEntity department : departmentEntities) {
            DepartmentHierarchyDTO departmentDTO = departmentDTOMap.get(department.getId());

            if (departmentDTO.parentId() == null) {
                roots.add(departmentDTO);
            } else {
                DepartmentHierarchyDTO parent = departmentDTOMap.get(departmentDTO.parentId());
                if (parent != null) {
                    parent.children().add(departmentDTO);
                }
            }
        }

        return roots;
    }
}
