package com.codymitra.shared_service.modules.bank_branch.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.bank.entities.BankEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "bank_branches")
@Table(name = "bank_branches")
@EqualsAndHashCode(callSuper = true)
public class BankBranchEntity extends BaseEntity {

    @Column(name = "branch_name", nullable = false)
    private String branchName;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private BankEntity bank;

    @Column(name = "active")
    private Boolean active;
}