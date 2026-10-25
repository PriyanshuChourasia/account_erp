package com.codymitra.shared_service.modules.salary_component.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.salary_component.enums.SalaryCalculationTypeEnum;
import com.codymitra.shared_service.modules.salary_component.enums.SalaryComponentTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "salary_components")
@Table(name = "salary_components")
@EqualsAndHashCode(callSuper = true)
public class SalaryComponentEntity extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "component_type", nullable = false)
    private SalaryComponentTypeEnum componentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "calculation_type", nullable = false)
    private SalaryCalculationTypeEnum calculationType;

    /// used when calculation type is FLAT_AMOUNT
    @Column(name = "amount", precision = 15, scale = 2)
    private BigDecimal amount;

    /// used when calculation type is PERCENTAGE, e.g. HRA = 40% of Basic
    @Column(name = "percentage", precision = 7, scale = 4)
    private BigDecimal percentage;

    @Comment("Salary component the percentage is calculated on")
    @Column(name = "base_component_id")
    private UUID baseComponentId;

    @Column(name = "taxable", nullable = false)
    private Boolean taxable;

    @Column(name = "affects_net_salary", nullable = false)
    private Boolean affectsNetSalary;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "active")
    private Boolean active;
}
