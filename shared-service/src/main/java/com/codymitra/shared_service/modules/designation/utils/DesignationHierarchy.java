package com.codymitra.shared_service.modules.designation.utils;

import com.codymitra.shared_service.modules.designation.dtos.DesignationHierarchyDTO;
import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;

import java.util.*;

public final class DesignationHierarchy {

    public static List<DesignationHierarchyDTO> designationDTO(List<DesignationEntity> designationEntities) {

        Map<UUID, DesignationHierarchyDTO> designationDTOMap = new HashMap<>();

        for (DesignationEntity designation : designationEntities) {
            designationDTOMap.put(designation.getId(),
                    new DesignationHierarchyDTO(
                            designation.getId(),
                            designation.getName(),
                            designation.getCode(),
                            designation.getParentId(),
                            designation.getDescription(),
                            designation.getActive(),
                            new ArrayList<>()
                    ));
        }

        List<DesignationHierarchyDTO> roots = new ArrayList<>();

        for (DesignationEntity designation : designationEntities) {
            DesignationHierarchyDTO designationDTO = designationDTOMap.get(designation.getId());

            if (designationDTO.parentId() == null) {
                roots.add(designationDTO);
            } else {
                DesignationHierarchyDTO parent = designationDTOMap.get(designationDTO.parentId());
                if (parent != null) {
                    parent.children().add(designationDTO);
                }
            }
        }

        return roots;
    }
}
