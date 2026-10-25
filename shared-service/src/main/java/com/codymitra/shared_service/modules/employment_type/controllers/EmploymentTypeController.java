package com.codymitra.shared_service.modules.employment_type.controllers;

import com.codymitra.shared_service.modules.employment_type.dtos.CreateEmploymentTypeRequest;
import com.codymitra.shared_service.modules.employment_type.dtos.EmploymentTypeDTO;
import com.codymitra.shared_service.modules.employment_type.services.EmploymentTypeService;
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
@RequestMapping(path = "/employment_types")
@RequiredArgsConstructor
public class EmploymentTypeController {

    private final EmploymentTypeService employmentTypeService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<EmploymentTypeDTO> dtos = employmentTypeService.getAll();
        String message = dtos.size() + " total employment types fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        EmploymentTypeDTO dto = employmentTypeService.getById(id);
        return ResponseHandler.generateResponse(dto, "Employment type fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateEmploymentTypeRequest request) {
        EmploymentTypeDTO dto = employmentTypeService.create(request);
        return ResponseHandler.generateResponse(dto, "Employment type created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateEmploymentTypeRequest request) {
        EmploymentTypeDTO dto = employmentTypeService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Employment type updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = employmentTypeService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
