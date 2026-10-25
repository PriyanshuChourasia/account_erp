package com.codymitra.shared_service.modules.document_type.services;

import com.codymitra.shared_service.modules.document_type.dtos.CreateDocumentTypeRequest;
import com.codymitra.shared_service.modules.document_type.dtos.DocumentTypeDTO;

import java.util.List;
import java.util.UUID;

public interface DocumentTypeService {

    List<DocumentTypeDTO> getAll();

    DocumentTypeDTO getById(UUID id);

    DocumentTypeDTO create(CreateDocumentTypeRequest request);

    DocumentTypeDTO update(UUID id, CreateDocumentTypeRequest request);

    String delete(UUID id);
}
