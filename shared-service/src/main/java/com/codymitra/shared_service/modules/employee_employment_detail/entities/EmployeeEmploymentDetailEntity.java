package com.codymitra.shared_service.modules.employee_employment_detail.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.employee.entities.EmployeeEntity;
import com.codymitra.shared_service.modules.employee.enums.EmployeeStatusEnum;
import com.codymitra.shared_service.modules.employment_type.entities.EmploymentTypeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "employee_employment_details")
@Table(name = "employee_employment_details")
@EqualsAndHashCode(callSuper = true)
public class EmployeeEmploymentDetailEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employment_type_id", nullable = false)
    private EmploymentTypeEntity employmentType;

    @Column(name = "date_of_joining", nullable = false)
    private LocalDate dateOfJoining;

    @Column(name = "date_of_exit")
    private LocalDate dateOfExit;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmployeeStatusEnum status;

    @Column(name = "active")
    private Boolean active;
}
