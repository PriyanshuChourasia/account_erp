package com.codymitra.shared_service.modules.stock_category.controllers;


import com.codymitra.shared_service.modules.stock_category.dtos.CreateStockCategoryRequest;
import com.codymitra.shared_service.modules.stock_category.dtos.StockCategoryDTO;
import com.codymitra.shared_service.modules.stock_category.dtos.StockCategoryHierarchyDTO;
import com.codymitra.shared_service.modules.stock_category.services.StockCategoryService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/stock_categories")
@RequiredArgsConstructor
public class StockCategoryController {

    private final StockCategoryService stockCategoryService;

    @GetMapping("/list")
    public ResponseEntity<Map<String,Object>> getAllStockCategories(){
        List<StockCategoryDTO> stockCategoryDTOS = stockCategoryService.getAllCategories();
        String message = stockCategoryDTOS.size()+" total stock categories fetched";
        return ResponseHandler.generateResponse(stockCategoryDTOS,message,HttpStatus.OK);
    }

    @GetMapping("/all-category-tree")
    public ResponseEntity<Map<String,Object>> getAllStockCategoryTree(){
        List<StockCategoryHierarchyDTO> stockCategoryHierarchyDTOS = stockCategoryService.getAllStockCategoryWithChildren();
        String message = stockCategoryHierarchyDTOS.size()+" total stock categories fetched";
        return ResponseHandler.generateResponse(stockCategoryHierarchyDTOS,message,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createStockCategory(@Valid @RequestBody CreateStockCategoryRequest createStockCategoryRequest){
        String message = stockCategoryService.create(createStockCategoryRequest);
        return ResponseHandler.generateResponse(message, HttpStatus.CREATED);
    }
}
