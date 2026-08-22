package com.codymitra.shared_service.modules.currency_numbering_system.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.codymitra.shared_service.modules.currency_numbering_system.dtos.CreateCurrencyNumberingSystemDTO;
import com.codymitra.shared_service.modules.currency_numbering_system.dtos.CurrencyNumberingSystemDTO;
import com.codymitra.shared_service.modules.currency_numbering_system.services.CurrencyNumberingSystemService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/currency_numbering_systems")
@RequiredArgsConstructor
public class CurrencyNumberingSystemController {

    private final CurrencyNumberingSystemService currencyNumberingSystemService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<CurrencyNumberingSystemDTO> dtos = currencyNumberingSystemService.getAll();
        String message = dtos.size() + " total currency numbering systems fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        CurrencyNumberingSystemDTO dto = currencyNumberingSystemService.getById(id);
        return ResponseHandler.generateResponse(dto, "Currency numbering system fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateCurrencyNumberingSystemDTO request) {
        CurrencyNumberingSystemDTO dto = currencyNumberingSystemService.create(request);
        return ResponseHandler.generateResponse(dto, "Currency numbering system created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateCurrencyNumberingSystemDTO request) {
        CurrencyNumberingSystemDTO dto = currencyNumberingSystemService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Currency numbering system updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = currencyNumberingSystemService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
