package com.codymitra.shared_service.modules.voucher_reference.entities;


import com.codymitra.shared_service.entities.BaseEntity;
import com.codymitra.shared_service.modules.voucher.entities.VoucherEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "voucher_references")
@Table(name = "voucher_references")
@EqualsAndHashCode(callSuper = true)
public class VoucherReferenceEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id", nullable = false)
    private VoucherEntity voucherId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_voucher_id", nullable = false)
    private VoucherEntity refVoucherId;
}
