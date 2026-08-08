package com.codymitra.shared_service.modules.stock_category.services;

import com.codymitra.shared_service.modules.stock_category.dtos.CreateStockCategoryRequest;
import com.codymitra.shared_service.modules.stock_category.dtos.StockCategoryDTO;

public interface StockCategoryService {

    StockCategoryDTO create(CreateStockCategoryRequest createStockCategoryRequest);
}
