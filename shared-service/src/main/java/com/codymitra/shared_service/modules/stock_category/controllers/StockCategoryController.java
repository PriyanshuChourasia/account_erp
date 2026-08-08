package com.codymitra.shared_service.modules.stock_category.controllers;


import com.codymitra.shared_service.modules.stock_category.dtos.CreateStockCategoryRequest;
import com.codymitra.shared_service.modules.stock_category.dtos.StockCategoryDTO;
import com.codymitra.shared_service.modules.stock_category.services.StockCategoryService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/stock_categories")
@RequiredArgsConstructor
public class StockCategoryController {

    private final StockCategoryService stockCategoryService;

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createStockCategory(@Valid @RequestBody CreateStockCategoryRequest createStockCategoryRequest){
        StockCategoryDTO stockCategoryDTO = stockCategoryService.create(createStockCategoryRequest);
        return ResponseHandler.generateResponse(stockCategoryDTO,"Stock Category Created successfully", HttpStatus.CREATED);
    }
}
