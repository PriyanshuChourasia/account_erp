package com.codymitra.shared_service.modules.document_status.controllers;

import com.codymitra.shared_service.modules.document_status.dtos.CreateDocumentStatusRequest;
import com.codymitra.shared_service.modules.document_status.dtos.DocumentStatusDTO;
import com.codymitra.shared_service.modules.document_status.services.DocumentStatusService;
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
@RequestMapping(path = "/document_statuses")
@RequiredArgsConstructor
public class DocumentStatusController {

    private final DocumentStatusService documentStatusService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<DocumentStatusDTO> dtos = documentStatusService.getAll();
        String message = dtos.size() + " total document statuses fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        DocumentStatusDTO dto = documentStatusService.getById(id);
        return ResponseHandler.generateResponse(dto, "Document status fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateDocumentStatusRequest request) {
        DocumentStatusDTO dto = documentStatusService.create(request);
        return ResponseHandler.generateResponse(dto, "Document status created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateDocumentStatusRequest request) {
        DocumentStatusDTO dto = documentStatusService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Document status updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = documentStatusService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
