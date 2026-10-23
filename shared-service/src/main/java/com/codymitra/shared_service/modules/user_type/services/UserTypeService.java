package com.codymitra.shared_service.modules.user_type.services;

import com.codymitra.shared_service.modules.user_type.dtos.CreateUserTypeRequest;
import com.codymitra.shared_service.modules.user_type.dtos.UserTypeDTO;

import java.util.List;
import java.util.UUID;

public interface UserTypeService {

    List<UserTypeDTO> getAll();

    UserTypeDTO getById(UUID id);

    UserTypeDTO create(CreateUserTypeRequest request);

    UserTypeDTO update(UUID id, CreateUserTypeRequest request);

    String delete(UUID id);
}
