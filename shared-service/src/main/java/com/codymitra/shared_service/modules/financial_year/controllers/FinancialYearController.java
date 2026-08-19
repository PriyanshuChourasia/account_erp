package com.codymitra.shared_service.modules.financial_year.controllers;

import java.util.UUID;
import com.codymitra.shared_service.modules.financial_year.dtos.CreateFinancialYearDTO;
import com.codymitra.shared_service.modules.financial_year.dtos.FinancialYearDTO;
import com.codymitra.shared_service.modules.financial_year.services.FinancialYearService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/financial-years")
@RequiredArgsConstructor
public class FinancialYearController {

    private final FinancialYearService financialYearService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<FinancialYearDTO> dtos = financialYearService.getAll();
        String message = dtos.size() + " total financial years fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<Map<String, Object>> updateCurrent(@Valid @RequestParam UUID id, @RequestParam Boolean current) {
        String message = financialYearService.updateCurrentFinancialYear(id,current);
        return ResponseHandler.generateResponse(message, "Financial year updated successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateFinancialYearDTO request) {
        FinancialYearDTO dto = financialYearService.create(request);
        return ResponseHandler.generateResponse(dto, "Financial year created successfully", HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateFinancialYearDTO request) {
//        FinancialYearDTO dto = financialYearService.update(id, request);
//        return ResponseHandler.generateResponse(dto, "Financial year updated successfully", HttpStatus.OK);
//    }
}
