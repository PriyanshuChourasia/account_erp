package com.codymitra.shared_service.modules.stock_category.services;

import com.codymitra.shared_service.modules.stock_category.dtos.CreateStockCategoryRequest;
import com.codymitra.shared_service.modules.stock_category.dtos.StockCategoryDTO;
import com.codymitra.shared_service.modules.stock_category.dtos.StockCategoryHierarchyDTO;

import java.util.List;

public interface StockCategoryService {

    List<StockCategoryDTO> getAllCategories();
    List<StockCategoryHierarchyDTO> getAllStockCategoryWithChildren();
    String create(CreateStockCategoryRequest createStockCategoryRequest);
}
