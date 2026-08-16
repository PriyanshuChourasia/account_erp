package com.codymitra.shared_service.modules.account_nature.services;

import com.codymitra.shared_service.modules.account_nature.dtos.AccountNatureDTO;
import com.codymitra.shared_service.modules.account_nature.dtos.CreateAccountNatureDTO;
import com.codymitra.shared_service.modules.account_nature.entities.AccountNatureEntity;

import java.util.List;

public interface AccountNatureService {

    List<AccountNatureDTO> getAllAccountNatures();

    String create(CreateAccountNatureDTO createAccountNatureDTO);

    AccountNatureEntity show(Long id);
}
