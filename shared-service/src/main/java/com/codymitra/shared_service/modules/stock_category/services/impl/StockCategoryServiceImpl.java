package com.codymitra.shared_service.modules.stock_category.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.modules.stock_category.dtos.CreateStockCategoryRequest;
import com.codymitra.shared_service.modules.stock_category.dtos.StockCategoryDTO;
import com.codymitra.shared_service.modules.stock_category.entities.StockCategoryEntity;
import com.codymitra.shared_service.modules.stock_category.mappers.StockCategoryMapper;
import com.codymitra.shared_service.modules.stock_category.repositories.StockCategoryRepository;
import com.codymitra.shared_service.modules.stock_category.services.StockCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockCategoryServiceImpl implements StockCategoryService {

    private final StockCategoryRepository stockCategoryRepository;

    @Override
    public StockCategoryDTO create(CreateStockCategoryRequest createStockCategoryRequest){

        if(stockCategoryRepository.existsByName(createStockCategoryRequest.name())){
            throw new DataAlreadyExistsException("Stock Category Already exists with this name");
        }
        StockCategoryEntity category = StockCategoryMapper.stockCategoryEntity(createStockCategoryRequest);
        StockCategoryEntity createCategory = stockCategoryRepository.save(category);
        return StockCategoryMapper.stockDTO(createCategory);
    }

}
