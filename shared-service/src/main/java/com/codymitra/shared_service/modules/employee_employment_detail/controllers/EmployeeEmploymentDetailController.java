package com.codymitra.shared_service.modules.employee_employment_detail.controllers;

import com.codymitra.shared_service.modules.employee_employment_detail.dtos.CreateEmployeeEmploymentDetailRequest;
import com.codymitra.shared_service.modules.employee_employment_detail.dtos.EmployeeEmploymentDetailDTO;
import com.codymitra.shared_service.modules.employee_employment_detail.services.EmployeeEmploymentDetailService;
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
@RequestMapping(path = "/employee_employment_details")
@RequiredArgsConstructor
public class EmployeeEmploymentDetailController {

    private final EmployeeEmploymentDetailService employeeEmploymentDetailService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<EmployeeEmploymentDetailDTO> dtos = employeeEmploymentDetailService.getAll();
        String message = dtos.size() + " total employee employment details fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        EmployeeEmploymentDetailDTO dto = employeeEmploymentDetailService.getById(id);
        return ResponseHandler.generateResponse(dto, "Employee employment detail fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateEmployeeEmploymentDetailRequest request) {
        EmployeeEmploymentDetailDTO dto = employeeEmploymentDetailService.create(request);
        return ResponseHandler.generateResponse(dto, "Employee employment detail created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateEmployeeEmploymentDetailRequest request) {
        EmployeeEmploymentDetailDTO dto = employeeEmploymentDetailService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Employee employment detail updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = employeeEmploymentDetailService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
