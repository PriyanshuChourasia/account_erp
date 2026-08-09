package com.codymitra.shared_service.modules.unit.services;

import com.codymitra.shared_service.modules.unit.dtos.CreateUnitRequestDTO;
import com.codymitra.shared_service.modules.unit.dtos.UnitDTO;

public interface UnitService {

    UnitDTO create(CreateUnitRequestDTO createUnitRequestDTO);
}
