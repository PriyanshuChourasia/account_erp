package com.codymitra.shared_service.modules.stock_unit.services;

import com.codymitra.shared_service.modules.stock_unit.dtos.CreateStockUnitRequestDTO;
import com.codymitra.shared_service.modules.stock_unit.dtos.StockUnitDTO;

public interface StockUnitService {

    StockUnitDTO create(CreateStockUnitRequestDTO createStockUnitRequestDTO);
}
