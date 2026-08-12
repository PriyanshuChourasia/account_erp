package com.codymitra.shared_service.modules.stock_unit.controllers;


import com.codymitra.shared_service.modules.stock_unit.dtos.CreateStockUnitRequestDTO;
import com.codymitra.shared_service.modules.stock_unit.dtos.StockUnitDTO;
import com.codymitra.shared_service.modules.stock_unit.services.StockUnitService;
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
@RequestMapping(path = "/stock_units")
@RequiredArgsConstructor
public class StockUnitController {

    private final StockUnitService stockUnitService;

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> create(@Valid @RequestBody CreateStockUnitRequestDTO createStockUnitRequestDTO){
        StockUnitDTO stockUnitDTO = stockUnitService.create(createStockUnitRequestDTO);
        return ResponseHandler.generateResponse(stockUnitDTO,"Unit created successfully", HttpStatus.CREATED);
    }
}
