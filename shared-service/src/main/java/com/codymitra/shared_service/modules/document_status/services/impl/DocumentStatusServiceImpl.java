package com.codymitra.shared_service.modules.document_status.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.document_status.dtos.CreateDocumentStatusRequest;
import com.codymitra.shared_service.modules.document_status.dtos.DocumentStatusDTO;
import com.codymitra.shared_service.modules.document_status.entities.DocumentStatusEntity;
import com.codymitra.shared_service.modules.document_status.mappers.DocumentStatusMapper;
import com.codymitra.shared_service.modules.document_status.repositories.DocumentStatusRepository;
import com.codymitra.shared_service.modules.document_status.services.DocumentStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentStatusServiceImpl implements DocumentStatusService {

    private final DocumentStatusRepository documentStatusRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DocumentStatusDTO> getAll() {
        return documentStatusRepository.findAll().stream()
                .map(DocumentStatusMapper::documentStatusDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentStatusDTO getById(UUID id) {
        return DocumentStatusMapper.documentStatusDTO(findById(id));
    }

    @Override
    @Transactional
    public DocumentStatusDTO create(CreateDocumentStatusRequest request) {
        validateUnique(request.name().trim(), DocumentStatusMapper.resolveCode(request.code()), null);
        return DocumentStatusMapper.documentStatusDTO(
                documentStatusRepository.save(DocumentStatusMapper.documentStatusEntity(request)));
    }

    @Override
    @Transactional
    public DocumentStatusDTO update(UUID id, CreateDocumentStatusRequest request) {
        DocumentStatusEntity entity = findById(id);
        validateUnique(request.name().trim(), DocumentStatusMapper.resolveCode(request.code()), id);
        return DocumentStatusMapper.documentStatusDTO(
                documentStatusRepository.save(DocumentStatusMapper.documentStatusEntity(entity, request)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        documentStatusRepository.delete(findById(id));
        return "Document status deleted successfully";
    }

    private DocumentStatusEntity findById(UUID id) {
        return documentStatusRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such document status found with this id")
        );
    }

    private void validateUnique(String name, String code, UUID id) {
        boolean nameTaken = id == null
                ? documentStatusRepository.existsByName(name)
                : documentStatusRepository.existsByNameAndIdNot(name, id);
        if (nameTaken) {
            throw new DataAlreadyExistsException("Document status already exists with this name");
        }

        if (code == null) {
            return;
        }
        boolean codeTaken = id == null
                ? documentStatusRepository.existsByCode(code)
                : documentStatusRepository.existsByCodeAndIdNot(code, id);
        if (codeTaken) {
            throw new DataAlreadyExistsException("Document status already exists with this code");
        }
    }
}
