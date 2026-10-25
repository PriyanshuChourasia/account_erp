package com.codymitra.shared_service.modules.document_type.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.document_type.dtos.CreateDocumentTypeRequest;
import com.codymitra.shared_service.modules.document_type.dtos.DocumentTypeDTO;
import com.codymitra.shared_service.modules.document_type.entities.DocumentTypeEntity;
import com.codymitra.shared_service.modules.document_type.mappers.DocumentTypeMapper;
import com.codymitra.shared_service.modules.document_type.repositories.DocumentTypeRepository;
import com.codymitra.shared_service.modules.document_type.services.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DocumentTypeDTO> getAll() {
        return documentTypeRepository.findAll().stream()
                .map(DocumentTypeMapper::documentTypeDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentTypeDTO getById(UUID id) {
        return DocumentTypeMapper.documentTypeDTO(findById(id));
    }

    @Override
    @Transactional
    public DocumentTypeDTO create(CreateDocumentTypeRequest request) {
        validateUnique(request.name().trim(), DocumentTypeMapper.resolveCode(request.code()), null);
        return DocumentTypeMapper.documentTypeDTO(
                documentTypeRepository.save(DocumentTypeMapper.documentTypeEntity(request)));
    }

    @Override
    @Transactional
    public DocumentTypeDTO update(UUID id, CreateDocumentTypeRequest request) {
        DocumentTypeEntity entity = findById(id);
        validateUnique(request.name().trim(), DocumentTypeMapper.resolveCode(request.code()), id);
        return DocumentTypeMapper.documentTypeDTO(
                documentTypeRepository.save(DocumentTypeMapper.documentTypeEntity(entity, request)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        documentTypeRepository.delete(findById(id));
        return "Document type deleted successfully";
    }

    private DocumentTypeEntity findById(UUID id) {
        return documentTypeRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such document type found with this id")
        );
    }

    private void validateUnique(String name, String code, UUID id) {
        boolean nameTaken = id == null
                ? documentTypeRepository.existsByName(name)
                : documentTypeRepository.existsByNameAndIdNot(name, id);
        if (nameTaken) {
            throw new DataAlreadyExistsException("Document type already exists with this name");
        }

        if (code == null) {
            return;
        }
        boolean codeTaken = id == null
                ? documentTypeRepository.existsByCode(code)
                : documentTypeRepository.existsByCodeAndIdNot(code, id);
        if (codeTaken) {
            throw new DataAlreadyExistsException("Document type already exists with this code");
        }
    }
}
