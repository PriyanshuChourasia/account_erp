package com.codymitra.shared_service.modules.employee_category.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "employee_categories")
@Table(name = "employee_categories")
@EqualsAndHashCode(callSuper = true)
public class EmployeeCategoryEntity extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "alias", unique = true)
    private String alias;

    @Column(name = "description")
    private String description;

    @Column(name = "allocate_revenue_item",nullable = false)
    private Boolean allocateRevenueItem;

    @Column(name = "allocate_non_revenue_item",nullable = false)
    private Boolean allocateNonRevenueItem;

    @Column(name = "active")
    private Boolean active;
}
