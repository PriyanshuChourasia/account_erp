package com.codymitra.shared_service.modules.inventory_type.entities;

import java.util.UUID;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "inventory_types")
@Table(name = "inventory_types")
@EqualsAndHashCode(callSuper = true)
public class InventoryTypeEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "parent_id")
    private UUID parentId;

    @Column(name = "description")
    private String description;
}
