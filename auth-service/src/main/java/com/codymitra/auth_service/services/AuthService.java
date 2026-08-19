package com.codymitra.auth_service.services;

import com.codymitra.auth_service.dtos.AuthRequestDTO;
import com.codymitra.auth_service.dtos.AuthResponseDTO;
import com.codymitra.auth_service.dtos.ProfileResponseDTO;

public interface AuthService {

    AuthResponseDTO authenticateUser(AuthRequestDTO authRequestDTO);
    ProfileResponseDTO profile(String token);
}
