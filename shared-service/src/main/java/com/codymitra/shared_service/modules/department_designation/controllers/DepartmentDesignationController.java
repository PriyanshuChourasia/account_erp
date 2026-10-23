package com.codymitra.shared_service.modules.department_designation.controllers;

import com.codymitra.shared_service.modules.department_designation.dtos.CreateDepartmentDesignationRequest;
import com.codymitra.shared_service.modules.department_designation.dtos.DepartmentDesignationDTO;
import com.codymitra.shared_service.modules.department_designation.services.DepartmentDesignationService;
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
@RequestMapping(path = "/department-designations")
@RequiredArgsConstructor
public class DepartmentDesignationController {

    private final DepartmentDesignationService departmentDesignationService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<DepartmentDesignationDTO> dtos = departmentDesignationService.getAll();
        String message = dtos.size() + " total department designations fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        DepartmentDesignationDTO dto = departmentDesignationService.getById(id);
        return ResponseHandler.generateResponse(dto, "Department designation fetched successfully", HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<Map<String, Object>> getByDepartmentId(@PathVariable UUID departmentId) {
        List<DepartmentDesignationDTO> dtos = departmentDesignationService.getByDepartmentId(departmentId);
        String message = dtos.size() + " total designations fetched for department";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/designation/{designationId}")
    public ResponseEntity<Map<String, Object>> getByDesignationId(@PathVariable UUID designationId) {
        List<DepartmentDesignationDTO> dtos = departmentDesignationService.getByDesignationId(designationId);
        String message = dtos.size() + " total departments fetched for designation";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateDepartmentDesignationRequest request) {
        DepartmentDesignationDTO dto = departmentDesignationService.create(request);
        return ResponseHandler.generateResponse(dto, "Designation assigned to department successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateDepartmentDesignationRequest request) {
        DepartmentDesignationDTO dto = departmentDesignationService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Department designation updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = departmentDesignationService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
