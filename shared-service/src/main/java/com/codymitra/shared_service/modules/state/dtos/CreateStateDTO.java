package com.codymitra.shared_service.modules.state.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateStateDTO(
        @NotBlank(message = "Name is required")
        String name,
        String code,
        @NotBlank(message = "Gst Code is required")
        String gstCode,
        @NotNull(message = "Country Id cannot be null")
        Long countryId,
        String description
) {}
