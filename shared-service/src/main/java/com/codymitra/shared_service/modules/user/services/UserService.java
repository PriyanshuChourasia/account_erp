package com.codymitra.shared_service.modules.user.services;

import com.codymitra.shared_service.modules.user.dtos.CreateUserDTO;
import com.codymitra.shared_service.modules.user.dtos.UpdateUserDTO;
import com.codymitra.shared_service.modules.user.dtos.UserDTO;
import com.codymitra.shared_service.modules.user.dtos.UserRegisterDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getById(UUID id);

    String create(CreateUserDTO createUserDTO);

    String update(UUID id, UpdateUserDTO updateUserDTO);

    String delete(UUID id);

    UserDTO register(UserRegisterDTO userRegisterDTO);
}
