package com.codymitra.shared_service.modules.account_group.entities;

import java.util.UUID;

import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.account_group.enums.AllocationMethodEnum;
import com.codymitra.shared_service.modules.account_nature.entities.AccountNatureEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "account_groups")
@Table(name = "account_groups")
@EqualsAndHashCode(callSuper = true)
public class AccountGroupEntity extends BaseEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "code")
    private Long code;

    @Column(name = "alias")
    private String alias;

    @Column(name = "parent_id")
    private UUID parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_nature_id")
    private AccountNatureEntity accountNatureId;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;


    /**
     * if this is a primary group
     */
    @Column(name = "is_group_is_sub_ledger")
    private Boolean isGroupIsSubLedger;

    //
    @Column(name = "is_nett_balance_for_reporting")
    private Boolean isNettBalanceForReporting;

    // Allocation Method
    @Column(name = "allocation_method")
    private AllocationMethodEnum allocationMethod;
}
