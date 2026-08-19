package com.codymitra.shared_service.modules.voucher_reference.dtos;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record VoucherReferenceDTO(
        UUID id,
        UUID voucherId,
        String voucherNo,
        LocalDate voucherDate,
        UUID refVoucherId,
        String refVoucherNo,
        LocalDate refVoucherDate
) {
}
