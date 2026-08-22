package com.codymitra.shared_service.modules.currency.controllers;

import com.codymitra.shared_service.modules.currency.dtos.CreateCurrencyDTO;
import com.codymitra.shared_service.modules.currency.dtos.CurrencyDTO;
import com.codymitra.shared_service.modules.currency.services.CurrencyService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<CurrencyDTO> dtos = currencyService.getAll();
        String message = dtos.size() + " total currencies fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateCurrencyDTO request) {
        CurrencyDTO dto = currencyService.create(request);
        return ResponseHandler.generateResponse(dto, "Currency created successfully", HttpStatus.CREATED);
    }
}
