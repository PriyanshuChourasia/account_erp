package com.codymitra.shared_service.modules.state.repositories;

import com.codymitra.shared_service.modules.state.entities.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long> {

    Boolean existsByCode(String code);
}
