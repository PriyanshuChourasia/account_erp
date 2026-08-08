package com.codymitra.shared_service.modules.unit.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.unit.enums.OperatorEnum;
import com.codymitra.shared_service.modules.unit.enums.UnitTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "code",unique = true)
    private String code;

    @Column(name = "unit_type")
    private UnitTypeEnum unitType;

    @Column(name = "operator")
    private OperatorEnum operator;

    @Column(name = "alias")
    private String alias;

    @Column(name = "base_unit_1_id")
    private Long baseUnit1Id;

    @Column(name = "base_unit_2_id")
    private Long baseUnit2Id;

    /// decimal 12,4
    @Column(name = "conversion_factor", precision = 12, scale = 4)
    private BigDecimal conversionFactor;

    /// tinyint
    @Column(name = "decimal_places")
    private Integer decimalPlaces;


}
