package com.codymitra.shared_service.modules.bank_account_detail.entities;

import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "bank_account_details")
@Table(name = "bank_account_details")
@EqualsAndHashCode(callSuper = true)
public class BankAccountDetailEntity extends BaseEntity {

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "account_holder_name", nullable = false)
    private String accountHolderName;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Column(name = "currency")
    private String currency;

    @Column(name = "active")
    private Boolean active;
}