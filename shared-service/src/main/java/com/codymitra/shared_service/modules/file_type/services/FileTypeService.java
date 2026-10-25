package com.codymitra.shared_service.modules.file_type.services;

import com.codymitra.shared_service.modules.file_type.dtos.CreateFileTypeRequest;
import com.codymitra.shared_service.modules.file_type.dtos.FileTypeDTO;

import java.util.List;
import java.util.UUID;

public interface FileTypeService {

    List<FileTypeDTO> getAll();

    FileTypeDTO getById(UUID id);

    FileTypeDTO create(CreateFileTypeRequest request);

    FileTypeDTO update(UUID id, CreateFileTypeRequest request);

    String delete(UUID id);
}