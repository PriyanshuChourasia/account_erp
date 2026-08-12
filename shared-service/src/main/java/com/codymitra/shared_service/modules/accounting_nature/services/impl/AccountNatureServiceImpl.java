package com.codymitra.shared_service.modules.accounting_nature.services.impl;


import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.accounting_nature.dtos.AccountNatureDTO;
import com.codymitra.shared_service.modules.accounting_nature.dtos.CreateAccountNatureDTO;
import com.codymitra.shared_service.modules.accounting_nature.entities.AccountNatureEntity;
import com.codymitra.shared_service.modules.accounting_nature.mappers.AccountNatureMapper;
import com.codymitra.shared_service.modules.accounting_nature.repositories.AccountNatureRepository;
import com.codymitra.shared_service.modules.accounting_nature.services.AccountNatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountNatureServiceImpl implements AccountNatureService {

    private final AccountNatureRepository accountNatureRepository;



    @Override
    public AccountNatureEntity show(Long id){
        return accountNatureRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Account Nature does not exists")
        );
    }

    @Override
    public List<AccountNatureDTO> getAllAccountNatures(){
        List<AccountNatureEntity> accountNatureEntities = accountNatureRepository.findAll();
        return accountNatureEntities.stream().map(AccountNatureMapper::accountNatureDTO).toList();
    }

    @Override
    public String create(CreateAccountNatureDTO createAccountNatureDTO){
        if(accountNatureRepository.existsByName(createAccountNatureDTO.name())){
            throw new DataAlreadyExistsException("Account Nature already exists");
        }
        accountNatureRepository.save(AccountNatureMapper.accountNatureEntity(createAccountNatureDTO));
        return "Account Nature created successfully";
    }
}
