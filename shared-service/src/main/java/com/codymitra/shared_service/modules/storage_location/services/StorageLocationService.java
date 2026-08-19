package com.codymitra.shared_service.modules.storage_location.services;

import java.util.UUID;
import com.codymitra.shared_service.modules.storage_location.dtos.CreateStorageLocationRequestDTO;
import com.codymitra.shared_service.modules.storage_location.dtos.StorageLocationDTO;
import com.codymitra.shared_service.modules.storage_location.dtos.StorageLocationHierarchyDTO;

import java.util.List;

public interface StorageLocationService {

    List<StorageLocationDTO> getAll();

    List<StorageLocationHierarchyDTO> getAllWithChildren();

    StorageLocationDTO getById(UUID id);

    StorageLocationDTO create(CreateStorageLocationRequestDTO request);

    StorageLocationDTO update(UUID id, CreateStorageLocationRequestDTO request);

    String delete(UUID id);
}
