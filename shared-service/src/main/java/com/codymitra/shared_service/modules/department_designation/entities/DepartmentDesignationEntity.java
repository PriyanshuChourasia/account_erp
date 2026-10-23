package com.codymitra.shared_service.modules.department_designation.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.department.entities.DepartmentEntity;
import com.codymitra.shared_service.modules.designation.entities.DesignationEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "department_designations")
@Table(name = "department_designations", uniqueConstraints = {
        @UniqueConstraint(name = "uk_department_designation", columnNames = {"department_id", "designation_id"})
})
@EqualsAndHashCode(callSuper = true)
public class DepartmentDesignationEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "designation_id", nullable = false)
    private DesignationEntity designation;

    @Column(name = "active")
    private Boolean active;
}
