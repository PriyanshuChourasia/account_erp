package com.codymitra.shared_service.modules.stock_category.repositories;


import com.codymitra.shared_service.modules.stock_category.entities.StockCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCategoryRepository extends JpaRepository<StockCategoryEntity,Long> {
}
