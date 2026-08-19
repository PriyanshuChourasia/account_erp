package com.codymitra.shared_service.modules.company_financial_year.controllers;

import java.util.UUID;
import com.codymitra.shared_service.modules.company_financial_year.dtos.CompanyFinancialYearDTO;
import com.codymitra.shared_service.modules.company_financial_year.dtos.CreateCompanyFinancialYearDTO;
import com.codymitra.shared_service.modules.company_financial_year.services.CompanyFinancialYearService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/company-financial-years")
@RequiredArgsConstructor
public class CompanyFinancialYearController {

    private final CompanyFinancialYearService companyFinancialYearService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<CompanyFinancialYearDTO> dtos = companyFinancialYearService.getAll();
        String message = dtos.size() + " total company financial years fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        CompanyFinancialYearDTO dto = companyFinancialYearService.getById(id);
        return ResponseHandler.generateResponse(dto, "Company financial year fetched successfully", HttpStatus.OK);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<Map<String, Object>> getByCompanyId(@PathVariable String companyId) {
        List<CompanyFinancialYearDTO> dtos = companyFinancialYearService.getByCompanyId(UUID.fromString(companyId));
        String message = dtos.size() + " total financial years fetched for company";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateCompanyFinancialYearDTO request) {
        CompanyFinancialYearDTO dto = companyFinancialYearService.create(request);
        return ResponseHandler.generateResponse(dto, "Company financial year created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateCompanyFinancialYearDTO request) {
        CompanyFinancialYearDTO dto = companyFinancialYearService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Company financial year updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = companyFinancialYearService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
