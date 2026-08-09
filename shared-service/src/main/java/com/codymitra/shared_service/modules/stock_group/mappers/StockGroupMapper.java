package com.codymitra.shared_service.modules.stock_group.mappers;

import com.codymitra.shared_service.modules.stock_group.dtos.CreateStockRequest;
import com.codymitra.shared_service.modules.stock_group.dtos.StockGroupDTO;
import com.codymitra.shared_service.modules.stock_group.entities.StockGroupEntity;

public final class StockGroupMapper {

    public static StockGroupDTO stockDTO(StockGroupEntity stockGroup){

        return new StockGroupDTO(
                stockGroup.getId(),
                stockGroup.getName(),
                stockGroup.getCode(),
                stockGroup.getAlias(),
                stockGroup.getDescription(),
                stockGroup.getActive(),
                stockGroup.getShouldAddQuantities(),
                stockGroup.getSetAlterGstDetails()
        );
    }

    public static StockGroupEntity stockGroupEntity(CreateStockRequest stockRequest){
        StockGroupEntity stockGroup = new StockGroupEntity();
        stockGroup.setName(stockRequest.name());
        stockGroup.setCode(stockRequest.code().toUpperCase());
        stockGroup.setAlias(stockRequest.alias());
        stockGroup.setDescription(stockRequest.description());
        if(stockRequest.parentId() != null){
            stockGroup.setParentId(stockRequest.parentId());
        }
        else{
            stockGroup.setParentId(null);
        }
        if(stockRequest.shouldAddQuantities() == null){
            stockGroup.setShouldAddQuantities(false);
        }
        else{
            stockGroup.setShouldAddQuantities(stockRequest.shouldAddQuantities());
        }

        if(stockRequest.setAlterGstDetail() == null){
            stockGroup.setSetAlterGstDetails(false);
        }
        else{
            stockGroup.setSetAlterGstDetails(stockRequest.setAlterGstDetail());
        }
        stockGroup.setActive(true);
        return stockGroup;
    }
}
