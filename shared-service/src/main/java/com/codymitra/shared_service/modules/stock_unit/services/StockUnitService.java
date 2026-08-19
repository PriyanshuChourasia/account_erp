package com.codymitra.shared_service.modules.stock_unit.services;

import java.util.UUID;
import com.codymitra.shared_service.modules.stock_unit.dtos.CreateStockUnitRequestDTO;
import com.codymitra.shared_service.modules.stock_unit.dtos.StockUnitDTO;
import com.codymitra.shared_service.modules.stock_unit.entities.StockUnitEntity;

import java.util.List;

public interface StockUnitService {

    List<StockUnitDTO> getAll();

    StockUnitDTO getById(UUID id);

    StockUnitEntity getEntityById(UUID id);

    StockUnitDTO create(CreateStockUnitRequestDTO createStockUnitRequestDTO);

    StockUnitDTO update(UUID id, CreateStockUnitRequestDTO createStockUnitRequestDTO);

    String delete(UUID id);
}
