package com.codymitra.shared_service.modules.account_group.services.impl;


import com.codymitra.shared_service.modules.account_group.dtos.AccountGroupDTO;
import com.codymitra.shared_service.modules.account_group.dtos.CreateAccountGroupDTO;
import com.codymitra.shared_service.modules.account_group.entities.AccountGroupEntity;
import com.codymitra.shared_service.modules.account_group.mapper.AccountGroupMapper;
import com.codymitra.shared_service.modules.account_group.repositories.AccountGroupRepository;
import com.codymitra.shared_service.modules.account_group.services.AccountGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountGroupServiceImpl implements AccountGroupService {

    private final AccountGroupRepository accountGroupRepository;


    @Override
    public List<AccountGroupDTO> getAllAccountGroup(){
        List<AccountGroupEntity> accountGroupEntities = accountGroupRepository.findAll();
        return accountGroupEntities.stream().map(AccountGroupMapper::accountGroupDTO).toList();
    }

    @Override
    public String create(CreateAccountGroupDTO createAccountGroupDTO){
        AccountGroupEntity accountGroupEntity = AccountGroupMapper.accountGroup(createAccountGroupDTO,true);
        return "Account Group created successfully";
    }
}
