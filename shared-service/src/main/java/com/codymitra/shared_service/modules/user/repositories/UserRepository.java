package com.codymitra.shared_service.modules.user.repositories;

import java.util.UUID;
import com.codymitra.shared_service.modules.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String username);
}
