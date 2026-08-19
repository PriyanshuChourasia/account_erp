package com.codymitra.shared_service.modules.account_group.services.impl;

import java.util.UUID;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.account_group.dtos.AccountGroupDTO;
import com.codymitra.shared_service.modules.account_group.dtos.CreateAccountGroupDTO;
import com.codymitra.shared_service.modules.account_group.entities.AccountGroupEntity;
import com.codymitra.shared_service.modules.account_group.mapper.AccountGroupMapper;
import com.codymitra.shared_service.modules.account_group.repositories.AccountGroupRepository;
import com.codymitra.shared_service.modules.account_group.services.AccountGroupService;
import com.codymitra.shared_service.modules.account_nature.entities.AccountNatureEntity;
import com.codymitra.shared_service.modules.account_nature.services.AccountNatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountGroupServiceImpl implements AccountGroupService {

    private final AccountGroupRepository accountGroupRepository;
    private final AccountNatureService natureService;


    @Override
    public List<AccountGroupDTO> getAllAccountGroup(){
        List<AccountGroupEntity> accountGroupEntities = accountGroupRepository.findAll();
        return accountGroupEntities.stream().map(AccountGroupMapper::accountGroupDTO).toList();
    }


    @Override
    public AccountGroupEntity getById(UUID id){
        return accountGroupRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Account Group does not exists with this id")
        );
    }

    @Override
    public String create(CreateAccountGroupDTO createAccountGroupDTO){
        AccountNatureEntity accountNature = natureService.show(createAccountGroupDTO.accountNatureId());
        AccountGroupEntity accountGroupEntity = AccountGroupMapper.accountGroup(createAccountGroupDTO,true,accountNature);
        accountGroupRepository.save(accountGroupEntity);
        return "Account Group created successfully";
    }
}
