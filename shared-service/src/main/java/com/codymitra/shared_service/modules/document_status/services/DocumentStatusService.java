package com.codymitra.shared_service.modules.document_status.services;

import com.codymitra.shared_service.modules.document_status.dtos.CreateDocumentStatusRequest;
import com.codymitra.shared_service.modules.document_status.dtos.DocumentStatusDTO;

import java.util.List;
import java.util.UUID;

public interface DocumentStatusService {

    List<DocumentStatusDTO> getAll();

    DocumentStatusDTO getById(UUID id);

    DocumentStatusDTO create(CreateDocumentStatusRequest request);

    DocumentStatusDTO update(UUID id, CreateDocumentStatusRequest request);

    String delete(UUID id);
}
