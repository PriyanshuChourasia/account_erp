package com.codymitra.shared_service.modules.document_type.mappers;

import com.codymitra.shared_service.modules.document_type.dtos.CreateDocumentTypeRequest;
import com.codymitra.shared_service.modules.document_type.dtos.DocumentTypeDTO;
import com.codymitra.shared_service.modules.document_type.entities.DocumentTypeEntity;

public final class DocumentTypeMapper {

    public static DocumentTypeDTO documentTypeDTO(DocumentTypeEntity entity) {
        return new DocumentTypeDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static DocumentTypeEntity documentTypeEntity(CreateDocumentTypeRequest request) {
        DocumentTypeEntity entity = applyRequest(new DocumentTypeEntity(), request);
        entity.setActive(true);
        return entity;
    }

    public static DocumentTypeEntity documentTypeEntity(DocumentTypeEntity entity, CreateDocumentTypeRequest request) {
        return applyRequest(entity, request);
    }

    public static String resolveCode(String code) {
        return code == null || code.isBlank() ? null : code.trim();
    }

    private static DocumentTypeEntity applyRequest(DocumentTypeEntity entity, CreateDocumentTypeRequest request) {
        entity.setName(request.name().trim());
        entity.setCode(resolveCode(request.code()));
        entity.setDescription(request.description());
        return entity;
    }
}
