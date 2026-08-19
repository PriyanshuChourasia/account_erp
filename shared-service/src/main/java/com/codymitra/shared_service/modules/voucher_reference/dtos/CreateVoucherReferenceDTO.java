package com.codymitra.shared_service.modules.voucher_reference.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateVoucherReferenceDTO(
        @NotBlank(message = "Voucher id is required")
        UUID voucherId,
        @NotBlank(message = "Reference voucher id is required")
        UUID refVoucherId
) {
}
