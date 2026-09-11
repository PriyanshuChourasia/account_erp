package com.codymitra.shared_service.modules.bank_account_detail.mappers;

import com.codymitra.shared_service.modules.bank_account_detail.dtos.BankAccountDetailDTO;
import com.codymitra.shared_service.modules.bank_account_detail.dtos.CreateBankAccountDetailRequest;
import com.codymitra.shared_service.modules.bank_account_detail.entities.BankAccountDetailEntity;

public final class BankAccountDetailMapper {

    public static BankAccountDetailDTO bankAccountDetailDTO(BankAccountDetailEntity entity) {
        return new BankAccountDetailDTO(
                entity.getId(),
                entity.getBankName(),
                entity.getAccountHolderName(),
                entity.getAccountNumber(),
                entity.getAccountType(),
                entity.getBranchName(),
                entity.getIfscCode(),
                entity.getCurrency(),
                entity.getActive()
        );
    }

    public static BankAccountDetailEntity bankAccountDetailEntity(CreateBankAccountDetailRequest request) {
        BankAccountDetailEntity entity = new BankAccountDetailEntity();
        entity.setBankName(request.bankName());
        entity.setAccountHolderName(request.accountHolderName());
        entity.setAccountNumber(request.accountNumber());
        entity.setAccountType(request.accountType());
        entity.setBranchName(request.branchName());
        entity.setIfscCode(request.ifscCode());
        entity.setCurrency(request.currency());
        entity.setActive(true);
        return entity;
    }
}