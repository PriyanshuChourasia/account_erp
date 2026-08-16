package com.codymitra.shared_service.modules.voucher_reference.repositories;

import com.codymitra.shared_service.modules.voucher_reference.entities.VoucherReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherReferenceRepository extends JpaRepository<VoucherReferenceEntity, Long> {

    Boolean existsByVoucherIdAndRefVoucherId(Long voucherId, Long refVoucherId);

    List<VoucherReferenceEntity> findByVoucherId(Long voucherId);

    List<VoucherReferenceEntity> findByRefVoucherId(Long refVoucherId);
}
