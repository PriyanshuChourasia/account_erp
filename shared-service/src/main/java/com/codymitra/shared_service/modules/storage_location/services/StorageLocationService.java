package com.codymitra.shared_service.modules.storage_location.services;

import com.codymitra.shared_service.modules.storage_location.dtos.CreateStorageLocationRequestDTO;
import com.codymitra.shared_service.modules.storage_location.dtos.StorageLocationDTO;
import com.codymitra.shared_service.modules.storage_location.dtos.StorageLocationHierarchyDTO;

import java.util.List;

public interface StorageLocationService {

    List<StorageLocationDTO> getAll();

    List<StorageLocationHierarchyDTO> getAllWithChildren();

    StorageLocationDTO getById(Long id);

    StorageLocationDTO create(CreateStorageLocationRequestDTO request);

    StorageLocationDTO update(Long id, CreateStorageLocationRequestDTO request);

    String delete(Long id);
}
