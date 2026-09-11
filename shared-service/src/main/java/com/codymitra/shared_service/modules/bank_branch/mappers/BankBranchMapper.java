package com.codymitra.shared_service.modules.bank_branch.mappers;

import com.codymitra.shared_service.modules.bank_branch.dtos.BankBranchDTO;
import com.codymitra.shared_service.modules.bank_branch.dtos.CreateBankBranchRequest;
import com.codymitra.shared_service.modules.bank_branch.entities.BankBranchEntity;

public final class BankBranchMapper {

    public static BankBranchDTO bankBranchDTO(BankBranchEntity entity) {
        return new BankBranchDTO(
                entity.getId(),
                entity.getBranchName(),
                entity.getBranchCode(),
                entity.getIfscCode(),
                entity.getAddress(),
                entity.getBank() != null ? entity.getBank().getId() : null,
                entity.getBank() != null ? entity.getBank().getName() : null,
                entity.getActive()
        );
    }

    public static BankBranchEntity bankBranchEntity(CreateBankBranchRequest request) {
        BankBranchEntity entity = new BankBranchEntity();
        entity.setBranchName(request.branchName());
        entity.setBranchCode(request.branchCode());
        entity.setIfscCode(request.ifscCode());
        entity.setAddress(request.address());
        entity.setActive(true);
        return entity;
    }
}