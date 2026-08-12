package com.codymitra.shared_service.modules.account_group.mapper;

import com.codymitra.shared_service.modules.account_group.dtos.AccountGroupDTO;
import com.codymitra.shared_service.modules.account_group.dtos.CreateAccountGroupDTO;
import com.codymitra.shared_service.modules.account_group.entities.AccountGroupEntity;
import com.codymitra.shared_service.modules.accounting_nature.entities.AccountNatureEntity;

public final class AccountGroupMapper {

    public static AccountGroupEntity accountGroup(CreateAccountGroupDTO createAccountGroupDTO, Boolean isActive, AccountNatureEntity accountNatureEntity){
        AccountGroupEntity accountGroupEntity = new AccountGroupEntity();
        accountGroupEntity.setName(createAccountGroupDTO.name());
        accountGroupEntity.setAlias(createAccountGroupDTO.alias());
        accountGroupEntity.setParentId(createAccountGroupDTO.parentId());
        accountGroupEntity.setAccountNatureId(accountNatureEntity);
        accountGroupEntity.setCode(createAccountGroupDTO.code());
        accountGroupEntity.setDescription(createAccountGroupDTO.description());
        accountGroupEntity.setActive(isActive);
        return accountGroupEntity;
    }

    public static AccountGroupDTO accountGroupDTO(AccountGroupEntity accountGroupEntity){
        return new AccountGroupDTO(
                accountGroupEntity.getId(),
                accountGroupEntity.getName(),
                accountGroupEntity.getAlias(),
                accountGroupEntity.getDescription(),
                accountGroupEntity.getActive()
        );
    }
}
