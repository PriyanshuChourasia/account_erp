package com.codymitra.shared_service.modules.voucher.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.voucher_type.entities.VoucherTypeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "vouchers")
@Table(name = "vouchers")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class VoucherEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "voucher_number")
    private String voucherNo;

    @Column(name = "voucher_date",columnDefinition = "DATE")
    private LocalDate voucherDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_type_id",nullable = false)
    private VoucherTypeEntity voucherTypeId;


    @Column(name = "voucher_reference_id")
    private Long voucherReferenceId;

    @Column(name = "account_ledger_id")
    private Long accountLedgerId;

    @Column(name = "remark")
    private String remark;
}
