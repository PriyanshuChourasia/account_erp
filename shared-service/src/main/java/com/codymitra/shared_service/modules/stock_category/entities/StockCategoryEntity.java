package com.codymitra.shared_service.modules.stock_category.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "stock_categories")
@Table(name = "stock_categories")
@EqualsAndHashCode(callSuper = true)
public class StockCategoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "code",unique = true)
    private String code;

    @Column(name = "alias")
    private String alias;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;
}
