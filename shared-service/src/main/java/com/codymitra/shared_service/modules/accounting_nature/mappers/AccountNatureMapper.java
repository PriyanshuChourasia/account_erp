package com.codymitra.shared_service.modules.accounting_nature.mappers;

import com.codymitra.shared_service.modules.accounting_nature.dtos.AccountNatureDTO;
import com.codymitra.shared_service.modules.accounting_nature.dtos.CreateAccountNatureDTO;
import com.codymitra.shared_service.modules.accounting_nature.entities.AccountNatureEntity;

public final class AccountNatureMapper {

    public static AccountNatureDTO accountNatureDTO(AccountNatureEntity accountNatureEntity){
        return new AccountNatureDTO(
                accountNatureEntity.getId(),
                accountNatureEntity.getName(),
                accountNatureEntity.getCode(),
                accountNatureEntity.getDescription()
        );
    }

    public static AccountNatureEntity accountNatureEntity(CreateAccountNatureDTO accountNatureDTO){
        AccountNatureEntity accountNature = new AccountNatureEntity();
        accountNature.setName(accountNatureDTO.name());
        accountNature.setCode(accountNatureDTO.code());
        accountNature.setDescription(accountNatureDTO.description());
        return accountNature;
    }
}
