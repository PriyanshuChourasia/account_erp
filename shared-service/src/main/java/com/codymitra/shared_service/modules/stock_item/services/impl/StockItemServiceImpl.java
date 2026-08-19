package com.codymitra.shared_service.modules.stock_item.services.impl;

import java.util.UUID;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.stock_category.entities.StockCategoryEntity;
import com.codymitra.shared_service.modules.stock_category.services.StockCategoryService;
import com.codymitra.shared_service.modules.stock_group.entities.StockGroupEntity;
import com.codymitra.shared_service.modules.stock_group.services.StockGroupService;
import com.codymitra.shared_service.modules.stock_item.dtos.CreateStockItemDTO;
import com.codymitra.shared_service.modules.stock_item.dtos.StockItemDTO;
import com.codymitra.shared_service.modules.stock_item.entities.StockItemEntity;
import com.codymitra.shared_service.modules.stock_item.mappers.StockItemMapper;
import com.codymitra.shared_service.modules.stock_item.repositories.StockItemRepository;
import com.codymitra.shared_service.modules.stock_item.services.StockItemService;
import com.codymitra.shared_service.modules.stock_unit.entities.StockUnitEntity;
import com.codymitra.shared_service.modules.stock_unit.services.StockUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockItemServiceImpl implements StockItemService {

    private final StockItemRepository stockItemRepository;
    private final StockGroupService stockGroupService;
    private final StockCategoryService stockCategoryService;
    private final StockUnitService stockUnitService;

    @Override
    public List<StockItemDTO> getAllStockItems(){
        List<StockItemEntity> stockItemEntities = stockItemRepository.findAll();
        return stockItemEntities.stream().map(StockItemMapper::stockItemDTO).toList();
    }

    @Override
    public StockItemEntity getById(UUID id){
        return stockItemRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Stock Item does not exists with this id")
        );
    }

    @Override
    public String create(CreateStockItemDTO createStockItemDTO){
        if(stockItemRepository.existsByName(createStockItemDTO.name())){
            throw new DataAlreadyExistsException("Stock Item already exists");
        }
        StockGroupEntity stockGroup = null;
        if(createStockItemDTO.stockGroupId() != null){
            stockGroup = stockGroupService.getById(createStockItemDTO.stockGroupId());
        }
        StockCategoryEntity stockCategory = null;
        if(createStockItemDTO.stockCategoryId() != null){
            stockCategory = stockCategoryService.getById(createStockItemDTO.stockCategoryId());
        }
        StockUnitEntity unit = stockUnitService.getEntityById(createStockItemDTO.unitId());
        StockUnitEntity altUnit = null;
        if(createStockItemDTO.altUnitId() != null){
            altUnit = stockUnitService.getEntityById(createStockItemDTO.altUnitId());
        }
        StockItemEntity stockItemEntity = StockItemMapper.stockItemEntity(createStockItemDTO, stockGroup, stockCategory, unit, altUnit);
        stockItemRepository.save(stockItemEntity);
        return "Stock Item created successfully";
    }
}
