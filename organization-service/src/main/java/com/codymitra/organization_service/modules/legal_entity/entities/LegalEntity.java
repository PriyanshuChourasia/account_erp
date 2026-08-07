package com.codymitra.organization_service.modules.legal_entity.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "legal_entities")
@Table(name = "legal_entities")
@EqualsAndHashCode(callSuper = true)
public class LegalEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "in_hierarchy")
    private Boolean inHierarchy;

    /// A short official note used for internal communication within business or organization
    @Column(name = "memo")
    private String memo;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;
}
