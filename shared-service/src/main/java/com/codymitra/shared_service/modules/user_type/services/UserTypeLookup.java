package com.codymitra.shared_service.modules.user_type.services;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.user_type.entities.UserTypeEntity;
import com.codymitra.shared_service.modules.user_type.enums.UserTypeCodeEnum;
import com.codymitra.shared_service.modules.user_type.repositories.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTypeLookup {

    private final UserTypeRepository userTypeRepository;

    public UserTypeEntity get(UserTypeCodeEnum code) {
        return userTypeRepository.findByCode(code.name()).orElseThrow(
                () -> new DataNotFoundException("User type " + code + " is not seeded")
        );
    }
}
