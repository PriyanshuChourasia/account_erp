package com.codymitra.shared_service.modules.stock_unit.services;

import com.codymitra.shared_service.modules.stock_unit.dtos.CreateStockUnitRequestDTO;
import com.codymitra.shared_service.modules.stock_unit.dtos.StockUnitDTO;

import java.util.List;

public interface StockUnitService {

    List<StockUnitDTO> getAll();

    StockUnitDTO getById(Long id);

    StockUnitDTO create(CreateStockUnitRequestDTO createStockUnitRequestDTO);

    StockUnitDTO update(Long id, CreateStockUnitRequestDTO createStockUnitRequestDTO);

    String delete(Long id);
}
