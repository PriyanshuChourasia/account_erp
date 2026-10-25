package com.codymitra.shared_service.modules.salary_component_type.controllers;

import com.codymitra.shared_service.modules.salary_component_type.dtos.CreateSalaryComponentTypeRequest;
import com.codymitra.shared_service.modules.salary_component_type.dtos.SalaryComponentTypeDTO;
import com.codymitra.shared_service.modules.salary_component_type.services.SalaryComponentTypeService;
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
@RequestMapping(path = "/salary_component_types")
@RequiredArgsConstructor
public class SalaryComponentTypeController {

    private final SalaryComponentTypeService salaryComponentTypeService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<SalaryComponentTypeDTO> dtos = salaryComponentTypeService.getAll();
        String message = dtos.size() + " total salary component types fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        SalaryComponentTypeDTO dto = salaryComponentTypeService.getById(id);
        return ResponseHandler.generateResponse(dto, "Salary component type fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateSalaryComponentTypeRequest request) {
        SalaryComponentTypeDTO dto = salaryComponentTypeService.create(request);
        return ResponseHandler.generateResponse(dto, "Salary component type created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateSalaryComponentTypeRequest request) {
        SalaryComponentTypeDTO dto = salaryComponentTypeService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Salary component type updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = salaryComponentTypeService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
