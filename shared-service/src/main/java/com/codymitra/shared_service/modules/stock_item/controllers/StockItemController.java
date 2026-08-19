package com.codymitra.shared_service.modules.stock_item.controllers;

import com.codymitra.shared_service.modules.stock_item.dtos.CreateStockItemDTO;
import com.codymitra.shared_service.modules.stock_item.dtos.StockItemDTO;
import com.codymitra.shared_service.modules.stock_item.services.StockItemService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/stock_items")
@RequiredArgsConstructor
public class StockItemController {

    private final StockItemService stockItemService;

    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllStockItems(){
        List<StockItemDTO> stockItemDTOS = stockItemService.getAllStockItems();
        String message = stockItemDTOS.size()+" stock items fetched successfully";
        return ResponseHandler.generateResponse(stockItemDTOS, message, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createStockItem(@Valid @RequestBody CreateStockItemDTO createStockItemDTO){
        String message = stockItemService.create(createStockItemDTO);
        return ResponseHandler.generateResponse(message, HttpStatus.CREATED);
    }
}
