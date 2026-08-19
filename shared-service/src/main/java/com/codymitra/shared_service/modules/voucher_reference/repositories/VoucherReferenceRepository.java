package com.codymitra.shared_service.modules.voucher_reference.repositories;

import java.util.UUID;
import com.codymitra.shared_service.modules.voucher_reference.entities.VoucherReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherReferenceRepository extends JpaRepository<VoucherReferenceEntity, UUID> {

    Boolean existsByVoucherIdAndRefVoucherId(UUID voucherId, UUID refVoucherId);

    List<VoucherReferenceEntity> findByVoucherId(UUID voucherId);

    List<VoucherReferenceEntity> findByRefVoucherId(UUID refVoucherId);
}
