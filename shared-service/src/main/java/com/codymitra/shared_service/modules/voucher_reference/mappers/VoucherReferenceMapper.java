package com.codymitra.shared_service.modules.voucher_reference.mappers;

import com.codymitra.shared_service.modules.voucher.entities.VoucherEntity;
import com.codymitra.shared_service.modules.voucher_reference.dtos.CreateVoucherReferenceDTO;
import com.codymitra.shared_service.modules.voucher_reference.dtos.VoucherReferenceDTO;
import com.codymitra.shared_service.modules.voucher_reference.entities.VoucherReferenceEntity;

public final class VoucherReferenceMapper {

    public static VoucherReferenceDTO voucherReferenceDTO(VoucherReferenceEntity voucherReference) {
        return new VoucherReferenceDTO(
                voucherReference.getId(),
                voucherReference.getVoucherId().getId(),
                voucherReference.getVoucherId().getVoucherNo(),
                voucherReference.getVoucherId().getVoucherDate(),
                voucherReference.getRefVoucherId().getId(),
                voucherReference.getRefVoucherId().getVoucherNo(),
                voucherReference.getRefVoucherId().getVoucherDate()
        );
    }

    public static VoucherReferenceEntity voucherReferenceEntity(CreateVoucherReferenceDTO request,
                                                                VoucherEntity voucher,
                                                                VoucherEntity refVoucher) {
        VoucherReferenceEntity voucherReference = new VoucherReferenceEntity();
        voucherReference.setVoucherId(voucher);
        voucherReference.setRefVoucherId(refVoucher);
        return voucherReference;
    }
}
