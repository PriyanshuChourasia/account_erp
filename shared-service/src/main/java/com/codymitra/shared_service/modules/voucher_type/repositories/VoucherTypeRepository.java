package com.codymitra.shared_service.modules.voucher_type.repositories;


import com.codymitra.shared_service.modules.voucher_type.entities.VoucherTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherTypeRepository extends JpaRepository<VoucherTypeEntity, Long> {
}
