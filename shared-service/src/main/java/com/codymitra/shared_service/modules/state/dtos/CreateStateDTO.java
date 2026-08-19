package com.codymitra.shared_service.modules.state.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateStateDTO(
        @NotBlank(message = "Name is required")
        String name,
        String code,
        @NotBlank(message = "Gst Code is required")
        String gstCode,
        @NotBlank(message = "Country Id cannot be null")
        UUID countryId,
        String description
) {}
