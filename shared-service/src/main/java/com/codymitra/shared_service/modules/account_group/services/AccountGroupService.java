package com.codymitra.shared_service.modules.account_group.services;

import com.codymitra.shared_service.modules.account_group.dtos.AccountGroupDTO;
import com.codymitra.shared_service.modules.account_group.dtos.CreateAccountGroupDTO;

import java.util.List;

public interface AccountGroupService {

    List<AccountGroupDTO> getAllAccountGroup();
    String create(CreateAccountGroupDTO createAccountGroupDTO);
}
