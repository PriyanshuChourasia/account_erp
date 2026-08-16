package com.codymitra.shared_service.modules.voucher_reference.mappers;

import com.codymitra.shared_service.modules.voucher.entities.VoucherEntity;
import com.codymitra.shared_service.modules.voucher_reference.dtos.CreateVoucherReferenceDTO;
import com.codymitra.shared_service.modules.voucher_reference.dtos.VoucherReferenceDTO;
import com.codymitra.shared_service.modules.voucher_reference.entities.VoucherReferenceEntity;

public final class VoucherReferenceMapper {

    public static VoucherReferenceDTO voucherReferenceDTO(VoucherReferenceEntity voucherReference) {
        return new VoucherReferenceDTO(
                voucherReference.getId(),
                voucherReference.getVoucher().getId(),
                voucherReference.getVoucher().getVoucherNo(),
                voucherReference.getVoucher().getVoucherDate(),
                voucherReference.getRefVoucher().getId(),
                voucherReference.getRefVoucher().getVoucherNo(),
                voucherReference.getRefVoucher().getVoucherDate()
        );
    }

    public static VoucherReferenceEntity voucherReferenceEntity(CreateVoucherReferenceDTO request,
                                                                VoucherEntity voucher,
                                                                VoucherEntity refVoucher) {
        VoucherReferenceEntity voucherReference = new VoucherReferenceEntity();
        voucherReference.setVoucher(voucher);
        voucherReference.setRefVoucher(refVoucher);
        return voucherReference;
    }
}
