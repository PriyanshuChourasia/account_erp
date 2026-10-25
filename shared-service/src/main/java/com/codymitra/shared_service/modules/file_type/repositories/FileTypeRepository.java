package com.codymitra.shared_service.modules.file_type.repositories;

import com.codymitra.shared_service.modules.file_type.entities.FileTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileTypeRepository extends JpaRepository<FileTypeEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCode(String code);

    Boolean existsByCodeAndIdNot(String code, UUID id);
}