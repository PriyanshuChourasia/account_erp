package com.codymitra.shared_service.modules.stock_item.repositories;


import com.codymitra.shared_service.modules.stock_item.entities.StockItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepository extends JpaRepository<StockItemEntity,Long> {
}
