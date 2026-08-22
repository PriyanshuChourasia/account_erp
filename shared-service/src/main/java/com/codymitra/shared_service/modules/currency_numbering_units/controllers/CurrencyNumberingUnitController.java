package com.codymitra.shared_service.modules.currency_numbering_units.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.codymitra.shared_service.modules.currency_numbering_units.dtos.CreateCurrencyNumberingUnitDTO;
import com.codymitra.shared_service.modules.currency_numbering_units.dtos.CurrencyNumberingUnitDTO;
import com.codymitra.shared_service.modules.currency_numbering_units.services.CurrencyNumberingUnitService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/currency_numbering_units")
@RequiredArgsConstructor
public class CurrencyNumberingUnitController {

    private final CurrencyNumberingUnitService currencyNumberingUnitService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<CurrencyNumberingUnitDTO> dtos = currencyNumberingUnitService.getAll();
        String message = dtos.size() + " total currency numbering units fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        CurrencyNumberingUnitDTO dto = currencyNumberingUnitService.getById(id);
        return ResponseHandler.generateResponse(dto, "Currency numbering unit fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateCurrencyNumberingUnitDTO request) {
        CurrencyNumberingUnitDTO dto = currencyNumberingUnitService.create(request);
        return ResponseHandler.generateResponse(dto, "Currency numbering unit created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateCurrencyNumberingUnitDTO request) {
        CurrencyNumberingUnitDTO dto = currencyNumberingUnitService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Currency numbering unit updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = currencyNumberingUnitService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
