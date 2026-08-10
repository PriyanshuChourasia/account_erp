package com.codymitra.shared_service.modules.stock_category.utils;

import com.codymitra.shared_service.modules.stock_category.dtos.StockCategoryHierarchyDTO;
import com.codymitra.shared_service.modules.stock_category.entities.StockCategoryEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class StockCategoryHierarchy {

    public static List<StockCategoryHierarchyDTO> stockCategoryDTO(List<StockCategoryEntity> stockCategoryEntities){

        Map<Long,StockCategoryHierarchyDTO> stockCategoryDTOMap = new HashMap<>();

        for(StockCategoryEntity stockCategory: stockCategoryEntities){
            stockCategoryDTOMap.put(stockCategory.getId(),
                    new StockCategoryHierarchyDTO(
                            stockCategory.getId(),
                            stockCategory.getName(),
                            stockCategory.getCode(),
                            stockCategory.getAlias(),
                            stockCategory.getParentId(),
                            stockCategory.getDescription(),
                            stockCategory.getActive(),
                            new ArrayList<>()
                    ));
        }

        List<StockCategoryHierarchyDTO> roots = new ArrayList<>();

        for(StockCategoryEntity stockCategory: stockCategoryEntities){
            StockCategoryHierarchyDTO stockCategoryDTO = stockCategoryDTOMap.get(stockCategory.getId());

            if(stockCategoryDTO.parentId() == null){
                roots.add(stockCategoryDTO);
            }
            else{
                StockCategoryHierarchyDTO parent = stockCategoryDTOMap.get(stockCategoryDTO.parentId());
                if(parent != null){
                    parent.children().add(stockCategoryDTO);
                }
            }
        }

        return roots;
    }
}
