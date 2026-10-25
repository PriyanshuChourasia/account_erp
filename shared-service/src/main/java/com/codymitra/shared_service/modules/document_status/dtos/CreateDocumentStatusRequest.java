package com.codymitra.shared_service.modules.document_status.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateDocumentStatusRequest(
        @NotBlank(message = "Name is required")
        String name,

        String code,

        String description
) {
}
