package com.codymitra.shared_service.modules.storage_unit.services;

import java.util.UUID;
import com.codymitra.shared_service.modules.storage_unit.dtos.StorageUnitDTO;
import com.codymitra.shared_service.modules.storage_unit.dtos.CreateStorageUnitDTO;
import com.codymitra.shared_service.modules.storage_unit.entities.StorageUnitEntity;

import java.util.List;

public interface StorageUnitService {

    List<StorageUnitDTO> getAllStorageUnits();

    String create(CreateStorageUnitDTO createStorageUnitDTO);

    StorageUnitEntity show(UUID id);
}
