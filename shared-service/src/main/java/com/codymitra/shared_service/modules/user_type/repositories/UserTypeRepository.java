package com.codymitra.shared_service.modules.user_type.repositories;

import com.codymitra.shared_service.modules.user_type.entities.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserTypeRepository extends JpaRepository<UserTypeEntity, UUID> {

    Optional<UserTypeEntity> findByCode(String code);

    Boolean existsByName(String name);

    Boolean existsByCode(String code);

    Boolean existsByNameAndIdNot(String name, UUID id);

    Boolean existsByCodeAndIdNot(String code, UUID id);
}
