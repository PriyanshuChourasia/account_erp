package com.codymitra.shared_service.modules.account_group.services;

import java.util.UUID;
import com.codymitra.shared_service.modules.account_group.dtos.AccountGroupDTO;
import com.codymitra.shared_service.modules.account_group.dtos.CreateAccountGroupDTO;
import com.codymitra.shared_service.modules.account_group.entities.AccountGroupEntity;

import java.util.List;

public interface AccountGroupService {

    List<AccountGroupDTO> getAllAccountGroup();

    String create(CreateAccountGroupDTO createAccountGroupDTO);

    AccountGroupEntity getById(UUID id);
}
