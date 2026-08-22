package com.codymitra.shared_service.modules.company.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.codymitra.shared_service.modules.company.dtos.CompanyDTO;
import com.codymitra.shared_service.modules.company.dtos.CreateCompanyDTO;
import com.codymitra.shared_service.modules.company.services.CompanyService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<CompanyDTO> dtos = companyService.getAll();
        String message = dtos.size() + " total companies fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        CompanyDTO dto = companyService.getById(id);
        return ResponseHandler.generateResponse(dto, "Company fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateCompanyDTO request) {
        CompanyDTO dto = companyService.create(request);
        return ResponseHandler.generateResponse(dto, "Company created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateCompanyDTO request) {
        CompanyDTO dto = companyService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Company updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = companyService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
