package com.codymitra.shared_service.modules.stock_unit.controllers;


import com.codymitra.shared_service.modules.stock_unit.dtos.CreateStockUnitRequestDTO;
import com.codymitra.shared_service.modules.stock_unit.dtos.StockUnitDTO;
import com.codymitra.shared_service.modules.stock_unit.services.StockUnitService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/stock_units")
@RequiredArgsConstructor
public class StockUnitController {

    private final StockUnitService stockUnitService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<StockUnitDTO> dtos = stockUnitService.getAll();
        String message = dtos.size() + " total units fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        StockUnitDTO dto = stockUnitService.getById(id);
        return ResponseHandler.generateResponse(dto, "Unit fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> create(@Valid @RequestBody CreateStockUnitRequestDTO createStockUnitRequestDTO){
        StockUnitDTO stockUnitDTO = stockUnitService.create(createStockUnitRequestDTO);
        return ResponseHandler.generateResponse(stockUnitDTO,"Unit created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Valid @RequestBody CreateStockUnitRequestDTO createStockUnitRequestDTO) {
        StockUnitDTO dto = stockUnitService.update(id, createStockUnitRequestDTO);
        return ResponseHandler.generateResponse(dto, "Unit updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        String message = stockUnitService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
