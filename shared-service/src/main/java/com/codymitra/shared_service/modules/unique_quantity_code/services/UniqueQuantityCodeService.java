package com.codymitra.shared_service.modules.unique_quantity_code.services;

import com.codymitra.shared_service.modules.unique_quantity_code.dtos.CreateUniqueQuantityCodeDTO;
import com.codymitra.shared_service.modules.unique_quantity_code.dtos.UniqueQuantityCodeDTO;

import java.util.List;

public interface UniqueQuantityCodeService {

    List<UniqueQuantityCodeDTO> getAll();

    UniqueQuantityCodeDTO getById(Long id);

    UniqueQuantityCodeDTO create(CreateUniqueQuantityCodeDTO request);

    UniqueQuantityCodeDTO update(Long id, CreateUniqueQuantityCodeDTO request);

    String delete(Long id);
}
