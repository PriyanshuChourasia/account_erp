package com.codymitra.shared_service.modules.employee_group.controllers;

import com.codymitra.shared_service.modules.employee_group.dtos.CreateEmployeeGroupRequest;
import com.codymitra.shared_service.modules.employee_group.dtos.EmployeeGroupDTO;
import com.codymitra.shared_service.modules.employee_group.services.EmployeeGroupService;
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
@RequestMapping(path = "/employee_groups")
@RequiredArgsConstructor
public class EmployeeGroupController {

    private final EmployeeGroupService employeeGroupService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<EmployeeGroupDTO> dtos = employeeGroupService.getAll();
        String message = dtos.size() + " total employee groups fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        EmployeeGroupDTO dto = employeeGroupService.getById(id);
        return ResponseHandler.generateResponse(dto, "Employee group fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateEmployeeGroupRequest request) {
        EmployeeGroupDTO dto = employeeGroupService.create(request);
        return ResponseHandler.generateResponse(dto, "Employee group created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateEmployeeGroupRequest request) {
        EmployeeGroupDTO dto = employeeGroupService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Employee group updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = employeeGroupService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
