package com.codymitra.shared_service.modules.storage_location.utils;

import com.codymitra.shared_service.modules.storage_location.dtos.StorageLocationHierarchyDTO;
import com.codymitra.shared_service.modules.storage_location.entities.StorageLocationEntity;

import java.util.*;

public final class StorageLocationHierarchy {

    public static List<StorageLocationHierarchyDTO> storageLocationDTO(List<StorageLocationEntity> storageLocationEntities) {

        Map<UUID, StorageLocationHierarchyDTO> storageLocationDTOMap = new HashMap<>();

        for (StorageLocationEntity storageLocation : storageLocationEntities) {
            storageLocationDTOMap.put(storageLocation.getId(),
                    new StorageLocationHierarchyDTO(
                            storageLocation.getId(),
                            storageLocation.getName(),
                            storageLocation.getCode(),
                            storageLocation.getAlias(),
                            storageLocation.getParentId(),
                            storageLocation.getDescription(),
                            storageLocation.getActive(),
                            new ArrayList<>()
                    ));
        }

        List<StorageLocationHierarchyDTO> roots = new ArrayList<>();

        for (StorageLocationEntity storageLocation : storageLocationEntities) {
            StorageLocationHierarchyDTO storageLocationDTO = storageLocationDTOMap.get(storageLocation.getId());

            if (storageLocationDTO.parentId() == null) {
                roots.add(storageLocationDTO);
            } else {
                StorageLocationHierarchyDTO parent = storageLocationDTOMap.get(storageLocationDTO.parentId());
                if (parent != null) {
                    parent.children().add(storageLocationDTO);
                }
            }
        }

        return roots;
    }
}
