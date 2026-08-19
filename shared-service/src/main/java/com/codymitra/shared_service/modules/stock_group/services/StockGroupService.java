package com.codymitra.shared_service.modules.stock_group.services;

import com.codymitra.shared_service.modules.stock_group.dtos.CreateStockRequest;
import com.codymitra.shared_service.modules.stock_group.dtos.StockGroupDTO;
import com.codymitra.shared_service.modules.stock_group.entities.StockGroupEntity;

import java.util.List;
import java.util.UUID;

public interface StockGroupService {

//    List<StockGroupDTO> getAllStockGroup();

    StockGroupDTO create(CreateStockRequest createStockRequest);

    StockGroupEntity getById(UUID id);
}
