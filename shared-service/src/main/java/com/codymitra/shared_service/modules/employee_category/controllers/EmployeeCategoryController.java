package com.codymitra.shared_service.modules.employee_category.controllers;

import com.codymitra.shared_service.modules.employee_category.dtos.CreateEmployeeCategoryRequest;
import com.codymitra.shared_service.modules.employee_category.dtos.EmployeeCategoryDTO;
import com.codymitra.shared_service.modules.employee_category.services.EmployeeCategoryService;
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
@RequestMapping(path = "/employee_categories")
@RequiredArgsConstructor
public class EmployeeCategoryController {

    private final EmployeeCategoryService employeeCategoryService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<EmployeeCategoryDTO> dtos = employeeCategoryService.getAll();
        String message = dtos.size() + " total employee categories fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        EmployeeCategoryDTO dto = employeeCategoryService.getById(id);
        return ResponseHandler.generateResponse(dto, "Employee category fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateEmployeeCategoryRequest request) {
        EmployeeCategoryDTO dto = employeeCategoryService.create(request);
        return ResponseHandler.generateResponse(dto, "Employee category created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateEmployeeCategoryRequest request) {
        EmployeeCategoryDTO dto = employeeCategoryService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Employee category updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = employeeCategoryService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
