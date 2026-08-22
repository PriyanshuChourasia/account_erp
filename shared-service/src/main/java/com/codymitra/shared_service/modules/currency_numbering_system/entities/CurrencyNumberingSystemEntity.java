package com.codymitra.shared_service.modules.currency_numbering_system.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "currency_numbering_systems")
@Table(name = "currency_numbering_systems")
@EqualsAndHashCode(callSuper = true)
public class CurrencyNumberingSystemEntity extends BaseEntity {

    /// e.g. Indian Numbering System, International Numbering System
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "code",nullable = false,unique = true)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "is_system")
    private Boolean isSystem;

    @Column(name = "active")
    private Boolean active;
}
