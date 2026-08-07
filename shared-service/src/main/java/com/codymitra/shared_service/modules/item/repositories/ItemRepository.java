package com.codymitra.shared_service.modules.item.repositories;


import com.codymitra.shared_service.modules.item.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Long> {
}
