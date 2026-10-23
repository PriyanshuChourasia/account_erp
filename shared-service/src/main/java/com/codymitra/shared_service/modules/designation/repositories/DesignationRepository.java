package com.codymitra.shared_service.modules.designation.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<DesignationEntity, UUID> {

    Boolean existsByName(String name);

    Boolean existsByCode(String code);

    Optional<DesignationEntity> findByCode(String code);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCodeAndIdNot(String code, UUID id);

    Boolean existsByParentId(UUID parentId);

    List<DesignationEntity> findAllByParentIdIsNull();
}
