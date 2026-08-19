package com.codymitra.shared_service.modules.unique_quantity_code.services;

import java.util.UUID;
import com.codymitra.shared_service.modules.unique_quantity_code.dtos.CreateUniqueQuantityCodeDTO;
import com.codymitra.shared_service.modules.unique_quantity_code.dtos.UniqueQuantityCodeDTO;

import java.util.List;

public interface UniqueQuantityCodeService {

    List<UniqueQuantityCodeDTO> getAll();

    UniqueQuantityCodeDTO getById(UUID id);

    UniqueQuantityCodeDTO create(CreateUniqueQuantityCodeDTO request);

    UniqueQuantityCodeDTO update(UUID id, CreateUniqueQuantityCodeDTO request);

    String delete(UUID id);
}
