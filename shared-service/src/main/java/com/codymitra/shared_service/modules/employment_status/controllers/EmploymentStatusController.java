package com.codymitra.shared_service.modules.employment_status.controllers;

import com.codymitra.shared_service.modules.employment_status.dtos.CreateEmploymentStatusRequest;
import com.codymitra.shared_service.modules.employment_status.dtos.EmploymentStatusDTO;
import com.codymitra.shared_service.modules.employment_status.services.EmploymentStatusService;
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
@RequestMapping(path = "/employment_statuses")
@RequiredArgsConstructor
public class EmploymentStatusController {

    private final EmploymentStatusService employmentStatusService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<EmploymentStatusDTO> dtos = employmentStatusService.getAll();
        String message = dtos.size() + " total employment statuses fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        EmploymentStatusDTO dto = employmentStatusService.getById(id);
        return ResponseHandler.generateResponse(dto, "Employment status fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateEmploymentStatusRequest request) {
        EmploymentStatusDTO dto = employmentStatusService.create(request);
        return ResponseHandler.generateResponse(dto, "Employment status created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateEmploymentStatusRequest request) {
        EmploymentStatusDTO dto = employmentStatusService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Employment status updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = employmentStatusService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
