package com.codymitra.shared_service.modules.stock_group.mappers;

import com.codymitra.shared_service.modules.stock_group.dtos.CreateStockRequest;
import com.codymitra.shared_service.modules.stock_group.dtos.StockGroupDTO;
import com.codymitra.shared_service.modules.stock_group.entities.StockGroupEntity;

public final class StockGroupMapper {

    public static StockGroupDTO stockDTO(StockGroupEntity stockGroup){

        return new StockGroupDTO(
                stockGroup.getId(),
                stockGroup.getName(),
                stockGroup.getAlias(),
                stockGroup.getParentId(),
                stockGroup.getDescription(),
                stockGroup.getShouldAddQuantities(),
                stockGroup.getSetAlterGstDetails(),
                stockGroup.getActive()
        );
    }

    public static StockGroupEntity stockGroupEntity(CreateStockRequest stockRequest){
        StockGroupEntity stockGroup = new StockGroupEntity();
        stockGroup.setName(stockRequest.name());
        stockGroup.setAlias(stockRequest.alias());
        stockGroup.setParentId(stockRequest.parentId());
        stockGroup.setDescription(stockRequest.description());
        stockGroup.setShouldAddQuantities(Boolean.TRUE.equals(stockRequest.shouldAddQuantities()));
        stockGroup.setSetAlterGstDetails(Boolean.TRUE.equals(stockRequest.setAlterGstDetails()));
        stockGroup.setActive(true);
        return stockGroup;
    }
}
