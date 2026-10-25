package com.codymitra.shared_service.modules.file_type.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.file_type.dtos.CreateFileTypeRequest;
import com.codymitra.shared_service.modules.file_type.dtos.FileTypeDTO;
import com.codymitra.shared_service.modules.file_type.entities.FileTypeEntity;
import com.codymitra.shared_service.modules.file_type.mappers.FileTypeMapper;
import com.codymitra.shared_service.modules.file_type.repositories.FileTypeRepository;
import com.codymitra.shared_service.modules.file_type.services.FileTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileTypeServiceImpl implements FileTypeService {

    private final FileTypeRepository fileTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FileTypeDTO> getAll() {
        return fileTypeRepository.findAll().stream()
                .map(FileTypeMapper::fileTypeDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FileTypeDTO getById(UUID id) {
        return FileTypeMapper.fileTypeDTO(findById(id));
    }

    @Override
    @Transactional
    public FileTypeDTO create(CreateFileTypeRequest request) {
        validateUnique(request.name().trim(), FileTypeMapper.resolveCode(request.code()), null);
        return FileTypeMapper.fileTypeDTO(fileTypeRepository.save(FileTypeMapper.fileTypeEntity(request)));
    }

    @Override
    @Transactional
    public FileTypeDTO update(UUID id, CreateFileTypeRequest request) {
        FileTypeEntity entity = findById(id);
        validateUnique(request.name().trim(), FileTypeMapper.resolveCode(request.code()), id);
        return FileTypeMapper.fileTypeDTO(fileTypeRepository.save(FileTypeMapper.fileTypeEntity(entity, request)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        fileTypeRepository.delete(findById(id));
        return "File type deleted successfully";
    }

    private FileTypeEntity findById(UUID id) {
        return fileTypeRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such file type found with this id")
        );
    }

    private void validateUnique(String name, String code, UUID id) {
        boolean nameTaken = id == null
                ? fileTypeRepository.existsByName(name)
                : fileTypeRepository.existsByNameAndIdNot(name, id);
        if (nameTaken) {
            throw new DataAlreadyExistsException("File type already exists with this name");
        }

        if (code == null) {
            return;
        }
        boolean codeTaken = id == null
                ? fileTypeRepository.existsByCode(code)
                : fileTypeRepository.existsByCodeAndIdNot(code, id);
        if (codeTaken) {
            throw new DataAlreadyExistsException("File type already exists with this code");
        }
    }
}