package com.codymitra.shared_service.modules.document_type.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateDocumentTypeRequest(
        @NotBlank(message = "Name is required")
        String name,

        String code,

        String description
) {
}
