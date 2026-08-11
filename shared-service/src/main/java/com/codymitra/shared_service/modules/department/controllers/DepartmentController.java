package com.codymitra.shared_service.modules.department.controllers;

import com.codymitra.shared_service.modules.department.dtos.CreateDepartmentRequest;
import com.codymitra.shared_service.modules.department.dtos.DepartmentDTO;
import com.codymitra.shared_service.modules.department.dtos.DepartmentHierarchyDTO;
import com.codymitra.shared_service.modules.department.services.DepartmentService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<DepartmentDTO> dtos = departmentService.getAll();
        String message = dtos.size() + " total departments fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/all-department-tree")
    public ResponseEntity<Map<String, Object>> getAllTree() {
        List<DepartmentHierarchyDTO> dtos = departmentService.getAllWithChildren();
        String message = dtos.size() + " total departments fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateDepartmentRequest request) {
        String message = departmentService.create(request);
        return ResponseHandler.generateResponse(message, HttpStatus.CREATED);
    }
}
