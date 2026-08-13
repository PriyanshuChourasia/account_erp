package com.codymitra.shared_service.modules.voucher_reference.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record VoucherReferenceDTO(
        Long id,
        Long voucherId,
        String voucherNo,
        LocalDate voucherDate,
        Long refVoucherId,
        String refVoucherNo,
        LocalDate refVoucherDate
) {
}
