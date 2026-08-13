package com.codymitra.shared_service.modules.voucher_reference.controllers;

import com.codymitra.shared_service.modules.voucher_reference.dtos.CreateVoucherReferenceDTO;
import com.codymitra.shared_service.modules.voucher_reference.dtos.VoucherReferenceDTO;
import com.codymitra.shared_service.modules.voucher_reference.services.VoucherReferenceService;
import com.codymitra.shared_service.responseHandler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/voucher_references")
@RequiredArgsConstructor
public class VoucherReferenceController {

    private final VoucherReferenceService voucherReferenceService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<VoucherReferenceDTO> dtos = voucherReferenceService.getAll();
        String message = dtos.size() + " total voucher references fetched";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        VoucherReferenceDTO dto = voucherReferenceService.getById(id);
        return ResponseHandler.generateResponse(dto, "Voucher reference fetched successfully", HttpStatus.OK);
    }

    @GetMapping("/voucher/{voucherId}")
    public ResponseEntity<Map<String, Object>> getByVoucherId(@PathVariable Long voucherId) {
        List<VoucherReferenceDTO> dtos = voucherReferenceService.getByVoucherId(voucherId);
        String message = dtos.size() + " total references fetched for voucher";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @GetMapping("/ref-voucher/{refVoucherId}")
    public ResponseEntity<Map<String, Object>> getByRefVoucherId(@PathVariable Long refVoucherId) {
        List<VoucherReferenceDTO> dtos = voucherReferenceService.getByRefVoucherId(refVoucherId);
        String message = dtos.size() + " total vouchers fetched referencing voucher";
        return ResponseHandler.generateResponse(dtos, message, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateVoucherReferenceDTO request) {
        VoucherReferenceDTO dto = voucherReferenceService.create(request);
        return ResponseHandler.generateResponse(dto, "Voucher reference created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Valid @RequestBody CreateVoucherReferenceDTO request) {
        VoucherReferenceDTO dto = voucherReferenceService.update(id, request);
        return ResponseHandler.generateResponse(dto, "Voucher reference updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        String message = voucherReferenceService.delete(id);
        return ResponseHandler.generateResponse(message, HttpStatus.OK);
    }
}
