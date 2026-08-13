package com.codymitra.shared_service.modules.voucher_reference.dtos;

import jakarta.validation.constraints.NotNull;

public record CreateVoucherReferenceDTO(
        @NotNull(message = "Voucher id is required")
        Long voucherId,
        @NotNull(message = "Reference voucher id is required")
        Long refVoucherId
) {
}
