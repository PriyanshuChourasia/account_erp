package com.codymitra.shared_service.modules.currency_minor_unit.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.codymitra.shared_service.modules.currency_minor_unit.dtos.CreateCurrencyMinorUnitDTO;
import com.codymitra.shared_service.modules.currency_minor_unit.dtos.CurrencyMinorUnitDTO;
import com.codymitra.shared_service.modules.currency_minor_unit.services.CurrencyMinorUnitService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/currency_minor_units")
@RequiredArgsConstructor
public class CurrencyMinorUnitController {

    private final CurrencyMinorUnitService currencyMinorUnitService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<CurrencyMinorUnitDTO> dtos = currencyMinorUnitService.getAll();
        String message = dtos.size() + " total currency minor units fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        CurrencyMinorUnitDTO dto = currencyMinorUnitService.getById(id);
        return ResponseHandler.generateResponse(dto, "Currency minor unit fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateCurrencyMinorUnitDTO request) {
        CurrencyMinorUnitDTO dto = currencyMinorUnitService.create(request);
        return ResponseHandler.generateResponse(dto, "Currency minor unit created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateCurrencyMinorUnitDTO request) {
        CurrencyMinorUnitDTO dto = currencyMinorUnitService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Currency minor unit updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = currencyMinorUnitService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
