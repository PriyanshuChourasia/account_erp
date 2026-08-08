package com.codymitra.shared_service.modules.stock_group.controllers;


import com.codymitra.shared_service.modules.stock_group.dtos.CreateStockRequest;
import com.codymitra.shared_service.modules.stock_group.dtos.StockGroupDTO;
import com.codymitra.shared_service.modules.stock_group.services.StockGroupService;
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
@RequestMapping(path = "/stock_groups")
@RequiredArgsConstructor
public class StockGroupController {

    private final StockGroupService stockGroupService;

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createStockGroup(@Valid @RequestBody CreateStockRequest createStockRequest){
        StockGroupDTO stockGroupDTO = stockGroupService.create(createStockRequest);
        return ResponseHandler.generateResponse(stockGroupDTO,"Stock Group Created successfully", HttpStatus.CREATED);
    }
}
