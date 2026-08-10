package com.codymitra.shared_service.modules.unit.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.unit.enums.UnitTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "units")
@Table(name = "units")
public class UnitEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",unique = true,nullable = false)
    private String name;

    @Column(name = "code",unique = true)
    private String code;

    @Column(name = "alias")
    private String alias;

    @Column(name = "description")
    private String description;

    ///  simple and compound
    @Comment("Compound will have two level of units either multiply or divide")
    @Column(name = "unit_type")
    @Enumerated(EnumType.STRING)
    private UnitTypeEnum unitType;

    /// example can be Box 1 piece conversion factor = 10 means 1 box = 10 piece
    @Column(name = "primary_unit_id")
    private Long primaryUnitId;

    @Column(name = "secondary_unit_id")
    private Long secondaryUnitId;

    /// decimal 12,4
    @Column(name = "conversion_factor", precision = 12, scale = 4)
    private BigDecimal conversionFactor;

    /// tinyint
    @Column(name = "decimal_places")
    private Integer decimalPlaces;

    @Column(name = "active")
    private Boolean active;
}
