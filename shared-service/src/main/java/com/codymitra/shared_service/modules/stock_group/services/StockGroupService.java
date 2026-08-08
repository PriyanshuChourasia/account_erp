package com.codymitra.shared_service.modules.stock_group.services;

import com.codymitra.shared_service.modules.stock_group.dtos.CreateStockRequest;
import com.codymitra.shared_service.modules.stock_group.dtos.StockGroupDTO;

import java.util.List;

public interface StockGroupService {

//    List<StockGroupDTO> getAllStockGroup();

    StockGroupDTO create(CreateStockRequest createStockRequest);
}
