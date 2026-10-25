package com.codymitra.shared_service.modules.contact_detail.controllers;

import com.codymitra.shared_service.modules.contact_detail.dtos.ContactDetailDTO;
import com.codymitra.shared_service.modules.contact_detail.dtos.CreateContactDetailRequest;
import com.codymitra.shared_service.modules.contact_detail.services.ContactDetailService;
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
@RequestMapping(path = "/contact_details")
@RequiredArgsConstructor
public class ContactDetailController {

    private final ContactDetailService contactDetailService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<ContactDetailDTO> dtos = contactDetailService.getAll();
        String message = dtos.size() + " total contact details fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable UUID id) {
        ContactDetailDTO dto = contactDetailService.getById(id);
        return ResponseHandler.generateResponse(dto, "Contact detail fetched successfully", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateContactDetailRequest request) {
        ContactDetailDTO dto = contactDetailService.create(request);
        return ResponseHandler.generateResponse(dto, "Contact detail created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @Valid @RequestBody CreateContactDetailRequest request) {
        ContactDetailDTO dto = contactDetailService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Contact detail updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable UUID id) {
        String message = contactDetailService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}