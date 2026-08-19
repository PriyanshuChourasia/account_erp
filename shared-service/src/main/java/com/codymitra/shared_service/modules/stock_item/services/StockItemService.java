package com.codymitra.shared_service.modules.stock_item.services;

import com.codymitra.shared_service.modules.stock_item.dtos.CreateStockItemDTO;
import com.codymitra.shared_service.modules.stock_item.dtos.StockItemDTO;
import com.codymitra.shared_service.modules.stock_item.entities.StockItemEntity;

import java.util.List;
import java.util.UUID;

public interface StockItemService {

    List<StockItemDTO> getAllStockItems();

    String create(CreateStockItemDTO createStockItemDTO);

    StockItemEntity getById(UUID id);
}
