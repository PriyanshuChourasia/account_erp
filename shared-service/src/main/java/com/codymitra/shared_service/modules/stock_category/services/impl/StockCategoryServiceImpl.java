package com.codymitra.shared_service.modules.stock_category.services.impl;

import java.util.UUID;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.stock_category.dtos.CreateStockCategoryRequest;
import com.codymitra.shared_service.modules.stock_category.dtos.StockCategoryDTO;
import com.codymitra.shared_service.modules.stock_category.dtos.StockCategoryHierarchyDTO;
import com.codymitra.shared_service.modules.stock_category.entities.StockCategoryEntity;
import com.codymitra.shared_service.modules.stock_category.mappers.StockCategoryMapper;
import com.codymitra.shared_service.modules.stock_category.repositories.StockCategoryRepository;
import com.codymitra.shared_service.modules.stock_category.services.StockCategoryService;
import com.codymitra.shared_service.modules.stock_category.utils.StockCategoryHierarchy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockCategoryServiceImpl implements StockCategoryService {

    private final StockCategoryRepository stockCategoryRepository;


    @Override
    public List<StockCategoryDTO> getAllCategories(){
        List<StockCategoryEntity> stockCategoryEntities = stockCategoryRepository.findAll();
        return stockCategoryEntities.stream().map(StockCategoryMapper::stockDTO).toList();
    }

    @Override
    public List<StockCategoryHierarchyDTO> getAllStockCategoryWithChildren(){
        List<StockCategoryEntity> stockCategoryEntities = stockCategoryRepository.findAll();

        return StockCategoryHierarchy.stockCategoryDTO(stockCategoryEntities);
    }


    @Override
    public String create(CreateStockCategoryRequest createStockCategoryRequest){

        if(stockCategoryRepository.existsByName(createStockCategoryRequest.name())){
            throw new DataAlreadyExistsException("Stock Category Already exists with this name");
        }
        StockCategoryEntity category = StockCategoryMapper.stockCategoryEntity(createStockCategoryRequest);
        StockCategoryEntity createCategory =         stockCategoryRepository.save(category);
        return "Stock Category Created successfully";
    }

    @Override
    public StockCategoryEntity getById(UUID id){
        return stockCategoryRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Stock Category does not exists with this id")
        );
    }
}
