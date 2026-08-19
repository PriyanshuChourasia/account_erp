package com.codymitra.shared_service.modules.stock_item.mappers;

import com.codymitra.shared_service.modules.stock_category.entities.StockCategoryEntity;
import com.codymitra.shared_service.modules.stock_group.entities.StockGroupEntity;
import com.codymitra.shared_service.modules.stock_item.dtos.CreateStockItemDTO;
import com.codymitra.shared_service.modules.stock_item.dtos.StockItemDTO;
import com.codymitra.shared_service.modules.stock_item.entities.StockItemEntity;
import com.codymitra.shared_service.modules.stock_unit.entities.StockUnitEntity;

public final class StockItemMapper {

    public static StockItemDTO stockItemDTO(StockItemEntity stockItemEntity){
        return new StockItemDTO(
                stockItemEntity.getId(),
                stockItemEntity.getName(),
                stockItemEntity.getCode(),
                stockItemEntity.getAlias(),
                stockItemEntity.getDescription(),
                stockItemEntity.getStockItemType(),
                stockItemEntity.getStockGroup() != null ? stockItemEntity.getStockGroup().getId() : null,
                stockItemEntity.getStockCategory() != null ? stockItemEntity.getStockCategory().getId() : null,
                stockItemEntity.getUnit() != null ? stockItemEntity.getUnit().getId() : null,
                stockItemEntity.getAltUnitId() != null ? stockItemEntity.getAltUnitId().getId() : null,
                stockItemEntity.getGstRate(),
                stockItemEntity.getPurchasePrice(),
                stockItemEntity.getSellingPrice(),
                stockItemEntity.getOpeningQuantity(),
                stockItemEntity.getOpeningValue(),
                stockItemEntity.getCurrentQuantity(),
                stockItemEntity.getReorderLevel(),
                stockItemEntity.getMinimumStock(),
                stockItemEntity.getMaximumStock(),
                stockItemEntity.getBarcode(),
                stockItemEntity.getHsn(),
                stockItemEntity.getSac(),
                stockItemEntity.getBrandId(),
                stockItemEntity.getTypeOfSupply(),
                stockItemEntity.getManufacturePartNo(),
                stockItemEntity.getArticleNo(),
                stockItemEntity.getSku(),
                stockItemEntity.getActive()
        );
    }

    public static StockItemEntity stockItemEntity(
            CreateStockItemDTO createStockItemDTO,
            StockGroupEntity stockGroup,
            StockCategoryEntity stockCategory,
            StockUnitEntity unit,
            StockUnitEntity altUnit
    ){
        StockItemEntity stockItem = new StockItemEntity();
        stockItem.setName(createStockItemDTO.name());
        stockItem.setCode(createStockItemDTO.code());
        stockItem.setAlias(createStockItemDTO.alias());
        stockItem.setDescription(createStockItemDTO.description());
        stockItem.setStockItemType(createStockItemDTO.stockItemType());
        stockItem.setStockGroup(stockGroup);
        stockItem.setStockCategory(stockCategory);
        stockItem.setUnit(unit);
        stockItem.setAltUnitId(altUnit);
        stockItem.setGstRate(createStockItemDTO.gstRate());
        stockItem.setPurchasePrice(createStockItemDTO.purchasePrice());
        stockItem.setSellingPrice(createStockItemDTO.sellingPrice());
        stockItem.setOpeningQuantity(createStockItemDTO.openingQuantity());
        stockItem.setOpeningValue(createStockItemDTO.openingValue());
        stockItem.setCurrentQuantity(createStockItemDTO.currentQuantity());
        stockItem.setReorderLevel(createStockItemDTO.reorderLevel());
        stockItem.setMinimumStock(createStockItemDTO.minimumStock());
        stockItem.setMaximumStock(createStockItemDTO.maximumStock());
        stockItem.setBarcode(createStockItemDTO.barcode());
        stockItem.setHsn(createStockItemDTO.hsn());
        stockItem.setSac(createStockItemDTO.sac());
        stockItem.setBrandId(createStockItemDTO.brandId());
        stockItem.setTypeOfSupply(createStockItemDTO.typeOfSupply());
        stockItem.setManufacturePartNo(createStockItemDTO.manufacturePartNo());
        stockItem.setArticleNo(createStockItemDTO.articleNo());
        stockItem.setSku(createStockItemDTO.sku());
        stockItem.setActive(createStockItemDTO.active());
        return stockItem;
    }
}
