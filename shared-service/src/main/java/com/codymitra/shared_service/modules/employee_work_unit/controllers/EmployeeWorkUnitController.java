package com.codymitra.shared_service.modules.employee_work_unit.controllers;

import com.codymitra.shared_service.modules.employee_work_unit.dtos.CreateEmployeeWorkUnitRequest;
import com.codymitra.shared_service.modules.employee_work_unit.dtos.EmployeeWorkUnitDTO;
import com.codymitra.shared_service.modules.employee_work_unit.services.EmployeeWorkUnitService;
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
@RequestMapping(path = "/employee_work_units")
@RequiredArgsConstructor
public class EmployeeWorkUnitController {

    private final EmployeeWorkUnitService employeeWorkUnitService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<EmployeeWorkUnitDTO> dtos = employeeWorkUnitService.getAll();
        String message = dtos.size() + " total employee work units fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        EmployeeWorkUnitDTO dto = employeeWorkUnitService.getById(id);
        return ResponseHandler.generateResponse(dto, "Employee work unit fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateEmployeeWorkUnitRequest request) {
        EmployeeWorkUnitDTO dto = employeeWorkUnitService.create(request);
        return ResponseHandler.generateResponse(dto, "Employee work unit created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateEmployeeWorkUnitRequest request) {
        EmployeeWorkUnitDTO dto = employeeWorkUnitService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Employee work unit updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = employeeWorkUnitService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
