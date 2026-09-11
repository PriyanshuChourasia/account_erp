package com.codymitra.shared_service.modules.bank_type.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


/*
while return for api return only children of bank types
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "bank_types")
@Table(name = "bank_types")
@EqualsAndHashCode(callSuper = true)
public class BankTypeEntity extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "code", unique = true,nullable = false)
    private String code;

    @Column(name = "parent_id")
    private UUID parentId;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;
}