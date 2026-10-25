package com.codymitra.shared_service.modules.salary_component.controllers;

import com.codymitra.shared_service.modules.salary_component.dtos.CreateSalaryComponentRequest;
import com.codymitra.shared_service.modules.salary_component.dtos.SalaryComponentDTO;
import com.codymitra.shared_service.modules.salary_component.services.SalaryComponentService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/salary_components")
@RequiredArgsConstructor
public class SalaryComponentController {

    private final SalaryComponentService salaryComponentService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<SalaryComponentDTO> dtos = salaryComponentService.getAll();
        String message = dtos.size() + " total salary components fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        SalaryComponentDTO dto = salaryComponentService.getById(id);
        return ResponseHandler.generateResponse(dto, "Salary component fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateSalaryComponentRequest request) {
        SalaryComponentDTO dto = salaryComponentService.create(request);
        return ResponseHandler.generateResponse(dto, "Salary component created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateSalaryComponentRequest request) {
        SalaryComponentDTO dto = salaryComponentService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Salary component updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = salaryComponentService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
