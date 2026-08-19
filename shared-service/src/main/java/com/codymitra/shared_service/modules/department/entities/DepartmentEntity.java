package com.codymitra.shared_service.modules.department.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "departments")
@Table(name = "departments")
@EqualsAndHashCode(callSuper = true)
public class DepartmentEntity extends BaseEntity {

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "parent_id")
    private UUID parentId;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;
}
