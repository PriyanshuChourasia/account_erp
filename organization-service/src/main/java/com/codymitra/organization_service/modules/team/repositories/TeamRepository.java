package com.codymitra.organization_service.modules.team.repositories;

import java.util.UUID;
import com.codymitra.organization_service.modules.team.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
}
