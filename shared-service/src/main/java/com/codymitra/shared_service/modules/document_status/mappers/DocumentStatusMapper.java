package com.codymitra.shared_service.modules.document_status.mappers;

import com.codymitra.shared_service.modules.document_status.dtos.CreateDocumentStatusRequest;
import com.codymitra.shared_service.modules.document_status.dtos.DocumentStatusDTO;
import com.codymitra.shared_service.modules.document_status.entities.DocumentStatusEntity;

public final class DocumentStatusMapper {

    public static DocumentStatusDTO documentStatusDTO(DocumentStatusEntity entity) {
        return new DocumentStatusDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static DocumentStatusEntity documentStatusEntity(CreateDocumentStatusRequest request) {
        DocumentStatusEntity entity = applyRequest(new DocumentStatusEntity(), request);
        entity.setActive(true);
        return entity;
    }

    public static DocumentStatusEntity documentStatusEntity(DocumentStatusEntity entity, CreateDocumentStatusRequest request) {
        return applyRequest(entity, request);
    }

    public static String resolveCode(String code) {
        return code == null || code.isBlank() ? null : code.trim();
    }

    private static DocumentStatusEntity applyRequest(DocumentStatusEntity entity, CreateDocumentStatusRequest request) {
        entity.setName(request.name().trim());
        entity.setCode(resolveCode(request.code()));
        entity.setDescription(request.description());
        return entity;
    }
}
