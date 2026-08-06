package com.codymitra.shared_service.modules.voucher.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

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

    @Column(name = "voucher_no")
    private String voucherNo;

    @Column(name = "date")
    private Instant date;

    @Column(name = "voucher_type_id")
    private Long voucherTypeId;


    @Column(name = "voucher_reference_id")
    private Long voucherReferenceId;

    @Column(name = "account_ledger_id")
    private Long accountLedgerId;

    @Column(name = "remark")
    private String remark;
}
