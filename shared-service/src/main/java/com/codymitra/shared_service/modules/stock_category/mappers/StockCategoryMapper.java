package com.codymitra.shared_service.modules.stock_category.mappers;

import com.codymitra.shared_service.modules.stock_category.dtos.CreateStockCategoryRequest;
import com.codymitra.shared_service.modules.stock_category.dtos.StockCategoryDTO;
import com.codymitra.shared_service.modules.stock_category.entities.StockCategoryEntity;

public final class StockCategoryMapper {

    public static StockCategoryDTO stockDTO(StockCategoryEntity stockCategory){

        return new StockCategoryDTO(
                stockCategory.getId(),
                stockCategory.getName(),
                stockCategory.getCode(),
                stockCategory.getAlias(),
                stockCategory.getParentId(),
                stockCategory.getDescription(),
                stockCategory.getActive()
        );
    }

    public static StockCategoryEntity stockCategoryEntity(CreateStockCategoryRequest stockRequest){
        StockCategoryEntity stockCategory = new StockCategoryEntity();
        stockCategory.setName(stockRequest.name());
        stockCategory.setCode(stockRequest.code().toUpperCase());
        stockCategory.setAlias(stockRequest.alias());
        stockCategory.setDescription(stockRequest.description());
        if(stockRequest.parentId() != null){
            stockCategory.setParentId(stockRequest.parentId());
        }
        else{
            stockCategory.setParentId(null);
        }
        stockCategory.setActive(true);
        return stockCategory;
    }
}
