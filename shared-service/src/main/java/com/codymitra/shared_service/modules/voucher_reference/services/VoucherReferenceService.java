package com.codymitra.shared_service.modules.voucher_reference.services;

import java.util.UUID;
import com.codymitra.shared_service.modules.voucher_reference.dtos.CreateVoucherReferenceDTO;
import com.codymitra.shared_service.modules.voucher_reference.dtos.VoucherReferenceDTO;

import java.util.List;

public interface VoucherReferenceService {

    List<VoucherReferenceDTO> getAll();

    VoucherReferenceDTO getById(UUID id);

    List<VoucherReferenceDTO> getByVoucherId(UUID voucherId);

    List<VoucherReferenceDTO> getByRefVoucherId(UUID refVoucherId);

    VoucherReferenceDTO create(CreateVoucherReferenceDTO request);

    VoucherReferenceDTO update(UUID id, CreateVoucherReferenceDTO request);

    String delete(UUID id);
}
