package com.codymitra.shared_service.modules.stock_group.controllers;


import com.codymitra.shared_service.modules.stock_group.dtos.CreateStockRequest;
import com.codymitra.shared_service.modules.stock_group.dtos.StockGroupDTO;
import com.codymitra.shared_service.modules.stock_group.services.StockGroupService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/stock_groups")
@RequiredArgsConstructor
public class StockGroupController {

    private final StockGroupService stockGroupService;


    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllStockGroup(){
        List<StockGroupDTO> stockGroupDTOS = stockGroupService.getAllStockGroup();
        String message = stockGroupDTOS.size() + " groups fetched";
        return ResponseHandler.generateResponse(stockGroupDTOS,message,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createStockGroup(@Valid @RequestBody CreateStockRequest createStockRequest){
        StockGroupDTO stockGroupDTO = stockGroupService.create(createStockRequest);
        return ResponseHandler.generateResponse(stockGroupDTO,"Stock Group Created successfully", HttpStatus.CREATED);
    }
}
