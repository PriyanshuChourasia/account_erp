package com.codymitra.organization_service.modules.internal_organization.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "internal_organizations")
@Table(name = "internal_organizations")
@EqualsAndHashCode(callSuper = true)
public class InternalOrganization extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;
}
