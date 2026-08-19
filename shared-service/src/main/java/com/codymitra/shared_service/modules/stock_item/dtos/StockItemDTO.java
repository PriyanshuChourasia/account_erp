package com.codymitra.shared_service.modules.stock_item.dtos;

import com.codymitra.shared_service.modules.stock_item.enums.StockItemTypeEnum;
import com.codymitra.shared_service.modules.stock_item.enums.TypeOfSupplyEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StockItemDTO(
        UUID id,
        String name,
        String code,
        String alias,
        String description,
        StockItemTypeEnum stockItemType,
        UUID stockGroupId,
        UUID stockCategoryId,
        UUID unitId,
        UUID altUnitId,
        Double gstRate,
        Double purchasePrice,
        Double sellingPrice,
        Double openingQuantity,
        Double openingValue,
        Double currentQuantity,
        Double reorderLevel,
        BigDecimal minimumStock,
        BigDecimal maximumStock,
        String barcode,
        String hsn,
        String sac,
        Long brandId,
        TypeOfSupplyEnum typeOfSupply,
        String manufacturePartNo,
        String articleNo,
        String sku,
        Boolean active
) {}
