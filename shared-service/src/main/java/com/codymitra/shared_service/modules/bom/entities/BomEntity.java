package com.codymitra.shared_service.modules.bom.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "boms")
@Table(name = "boms")
@EqualsAndHashCode(callSuper = true)
public class BomEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;
}
