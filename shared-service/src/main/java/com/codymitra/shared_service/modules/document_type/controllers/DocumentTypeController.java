package com.codymitra.shared_service.modules.document_type.controllers;

import com.codymitra.shared_service.modules.document_type.dtos.CreateDocumentTypeRequest;
import com.codymitra.shared_service.modules.document_type.dtos.DocumentTypeDTO;
import com.codymitra.shared_service.modules.document_type.services.DocumentTypeService;
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
@RequestMapping(path = "/document_types")
@RequiredArgsConstructor
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<DocumentTypeDTO> dtos = documentTypeService.getAll();
        String message = dtos.size() + " total document types fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        DocumentTypeDTO dto = documentTypeService.getById(id);
        return ResponseHandler.generateResponse(dto, "Document type fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateDocumentTypeRequest request) {
        DocumentTypeDTO dto = documentTypeService.create(request);
        return ResponseHandler.generateResponse(dto, "Document type created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateDocumentTypeRequest request) {
        DocumentTypeDTO dto = documentTypeService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Document type updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = documentTypeService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
