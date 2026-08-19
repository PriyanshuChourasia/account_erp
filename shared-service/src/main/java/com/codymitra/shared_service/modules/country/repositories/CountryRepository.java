package com.codymitra.shared_service.modules.country.repositories;

import java.util.UUID;
import com.codymitra.shared_service.modules.country.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {

    Boolean existsByName(String name);
}
