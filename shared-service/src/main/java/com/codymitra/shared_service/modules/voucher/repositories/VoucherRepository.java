package com.codymitra.shared_service.modules.voucher.repositories;


import com.codymitra.shared_service.modules.voucher.entities.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<VoucherEntity,Long> {
}
