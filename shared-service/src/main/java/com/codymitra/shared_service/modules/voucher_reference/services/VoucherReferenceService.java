package com.codymitra.shared_service.modules.voucher_reference.services;

import com.codymitra.shared_service.modules.voucher_reference.dtos.CreateVoucherReferenceDTO;
import com.codymitra.shared_service.modules.voucher_reference.dtos.VoucherReferenceDTO;

import java.util.List;

public interface VoucherReferenceService {

    List<VoucherReferenceDTO> getAll();

    VoucherReferenceDTO getById(Long id);

    List<VoucherReferenceDTO> getByVoucherId(Long voucherId);

    List<VoucherReferenceDTO> getByRefVoucherId(Long refVoucherId);

    VoucherReferenceDTO create(CreateVoucherReferenceDTO request);

    VoucherReferenceDTO update(Long id, CreateVoucherReferenceDTO request);

    String delete(Long id);
}
