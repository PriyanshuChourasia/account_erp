package com.codymitra.shared_service.modules.bank_branch.repositories;

import com.codymitra.shared_service.modules.bank_branch.entities.BankBranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankBranchRepository extends JpaRepository<BankBranchEntity, UUID> {
    Boolean existsByBranchName(String branchName);
    Boolean existsByIfscCode(String ifscCode);
}