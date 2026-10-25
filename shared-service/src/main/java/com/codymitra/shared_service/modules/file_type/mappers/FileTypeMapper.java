package com.codymitra.shared_service.modules.file_type.mappers;

import com.codymitra.shared_service.modules.file_type.dtos.CreateFileTypeRequest;
import com.codymitra.shared_service.modules.file_type.dtos.FileTypeDTO;
import com.codymitra.shared_service.modules.file_type.entities.FileTypeEntity;

public final class FileTypeMapper {

    public static FileTypeDTO fileTypeDTO(FileTypeEntity entity) {
        return new FileTypeDTO(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getDescription(),
                entity.getActive()
        );
    }

    public static FileTypeEntity fileTypeEntity(CreateFileTypeRequest request) {
        FileTypeEntity entity = applyRequest(new FileTypeEntity(), request);
        entity.setActive(true);
        return entity;
    }

    public static FileTypeEntity fileTypeEntity(FileTypeEntity entity, CreateFileTypeRequest request) {
        return applyRequest(entity, request);
    }

    public static String resolveCode(String code) {
        return code == null || code.isBlank() ? null : code.trim();
    }

    private static FileTypeEntity applyRequest(FileTypeEntity entity, CreateFileTypeRequest request) {
        entity.setName(request.name().trim());
        entity.setCode(resolveCode(request.code()));
        entity.setDescription(request.description());
        return entity;
    }
}