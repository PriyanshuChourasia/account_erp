package com.codymitra.shared_service.modules.stock_category.services;

import com.codymitra.shared_service.modules.stock_category.dtos.CreateStockCategoryRequest;
import com.codymitra.shared_service.modules.stock_category.dtos.StockCategoryDTO;
import com.codymitra.shared_service.modules.stock_category.dtos.StockCategoryHierarchyDTO;
import com.codymitra.shared_service.modules.stock_category.entities.StockCategoryEntity;

import java.util.List;
import java.util.UUID;

public interface StockCategoryService {

    List<StockCategoryDTO> getAllCategories();
    List<StockCategoryHierarchyDTO> getAllStockCategoryWithChildren();
    String create(CreateStockCategoryRequest createStockCategoryRequest);
    StockCategoryEntity getById(UUID id);
}
