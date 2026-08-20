package com.codymitra.shared_service.modules.user.mappers;

import com.codymitra.shared_service.modules.user.dtos.CreateUserDTO;
import com.codymitra.shared_service.modules.user.dtos.UpdateUserDTO;
import com.codymitra.shared_service.modules.user.dtos.UserDTO;
import com.codymitra.shared_service.modules.user.entities.UserEntity;

public final class UserMapper {

    public static UserDTO userDTO(UserEntity entity) {
        return new UserDTO(
                entity.getId(),
                entity.getName(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getContactNo(),
                entity.getAltContactNo(),
                entity.getCode(),
                entity.getDateOfBirth(),
                entity.getActive()
        );
    }

    public static UserEntity userEntity(CreateUserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setContactNo(dto.contactNo());
        entity.setAltContactNo(dto.altContactNo());
        entity.setPassword(dto.password());
        entity.setDateOfBirth(dto.dateOfBirth());
        entity.setActive(true);
        return entity;
    }

    public static void updateEntity(UpdateUserDTO dto, UserEntity entity) {
        if (dto.name() != null) entity.setName(dto.name());
        if (dto.email() != null) entity.setEmail(dto.email());
        if (dto.contactNo() != null) entity.setContactNo(dto.contactNo());
        if (dto.altContactNo() != null) entity.setAltContactNo(dto.altContactNo());
        if (dto.password() != null) entity.setPassword(dto.password());
        if (dto.dateOfBirth() != null) entity.setDateOfBirth(dto.dateOfBirth());
        if (dto.active() != null) entity.setActive(dto.active());
    }
}
