package com.codymitra.auth_service.mapper;

import com.codymitra.auth_service.dtos.ProfileResponseDTO;
import com.codymitra.shared_service.modules.user.entities.UserEntity;

public final class AuthMapper {

    public static ProfileResponseDTO profileResponseDTO(UserEntity user){
        return new ProfileResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getContactNo()
        );
    }
}
