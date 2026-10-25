package com.codymitra.shared_service.modules.file_type.controllers;

import com.codymitra.shared_service.modules.file_type.dtos.CreateFileTypeRequest;
import com.codymitra.shared_service.modules.file_type.dtos.FileTypeDTO;
import com.codymitra.shared_service.modules.file_type.services.FileTypeService;
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
@RequestMapping(path = "/file_types")
@RequiredArgsConstructor
public class FileTypeController {

    private final FileTypeService fileTypeService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<FileTypeDTO> dtos = fileTypeService.getAll();
        String message = dtos.size() + " total file types fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        FileTypeDTO dto = fileTypeService.getById(id);
        return ResponseHandler.generateResponse(dto, "File type fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateFileTypeRequest request) {
        FileTypeDTO dto = fileTypeService.create(request);
        return ResponseHandler.generateResponse(dto, "File type created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateFileTypeRequest request) {
        FileTypeDTO dto = fileTypeService.update(id, request);
        return ResponseHandler.generateResponse(dto, "File type updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = fileTypeService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}