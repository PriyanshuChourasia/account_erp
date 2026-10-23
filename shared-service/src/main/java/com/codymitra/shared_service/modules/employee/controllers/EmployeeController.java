package com.codymitra.shared_service.modules.employee.controllers;

import com.codymitra.shared_service.modules.employee.dtos.CreateEmployeeRequest;
import com.codymitra.shared_service.modules.employee.dtos.EmployeeDTO;
import com.codymitra.shared_service.modules.employee.services.EmployeeService;
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
@RequestMapping(path = "/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<EmployeeDTO> dtos = employeeService.getAll();
        String message = dtos.size() + " total employees fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        EmployeeDTO dto = employeeService.getById(id);
        return ResponseHandler.generateResponse(dto, "Employee fetched successfully", HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<Map<String, Object>> getByDepartment(@PathVariable UUID departmentId) {
        List<EmployeeDTO> dtos = employeeService.getByDepartment(departmentId);
        String message = dtos.size() + " employees fetched for this department";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateEmployeeRequest request) {
        EmployeeDTO dto = employeeService.create(request);
        return ResponseHandler.generateResponse(dto, "Employee created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateEmployeeRequest request) {
        EmployeeDTO dto = employeeService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Employee updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = employeeService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
