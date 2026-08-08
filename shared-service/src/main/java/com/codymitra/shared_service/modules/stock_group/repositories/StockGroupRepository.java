package com.codymitra.shared_service.modules.stock_group.repositories;

import com.codymitra.shared_service.modules.stock_group.entities.StockGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockGroupRepository extends JpaRepository<StockGroupEntity,Long> {

    Boolean existsByName(String name);

    Boolean existsByCode(String code);
}
