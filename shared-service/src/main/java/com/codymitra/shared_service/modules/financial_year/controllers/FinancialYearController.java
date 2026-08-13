package com.codymitra.shared_service.modules.financial_year.controllers;

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

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        FinancialYearDTO dto = financialYearService.getById(id);
        return ResponseHandler.generateResponse(dto, "Financial year fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateFinancialYearDTO request) {
        FinancialYearDTO dto = financialYearService.create(request);
        return ResponseHandler.generateResponse(dto, "Financial year created successfully", HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Valid @RequestBody CreateFinancialYearDTO request) {
//        FinancialYearDTO dto = financialYearService.update(id, request);
//        return ResponseHandler.generateResponse(dto, "Financial year updated successfully", HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        String message = financialYearService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
