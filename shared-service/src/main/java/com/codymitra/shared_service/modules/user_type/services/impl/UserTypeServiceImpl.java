package com.codymitra.shared_service.modules.user_type.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.user_type.dtos.CreateUserTypeRequest;
import com.codymitra.shared_service.modules.user_type.dtos.UserTypeDTO;
import com.codymitra.shared_service.modules.user_type.entities.UserTypeEntity;
import com.codymitra.shared_service.modules.user_type.mappers.UserTypeMapper;
import com.codymitra.shared_service.modules.user_type.repositories.UserTypeRepository;
import com.codymitra.shared_service.modules.user_type.services.UserTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeRepository userTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserTypeDTO> getAll() {
        return userTypeRepository.findAll().stream()
                .map(UserTypeMapper::userTypeDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserTypeDTO getById(UUID id) {
        return UserTypeMapper.userTypeDTO(findById(id));
    }

    @Override
    @Transactional
    public UserTypeDTO create(CreateUserTypeRequest request) {
        validateUnique(request.name(), UserTypeMapper.resolveCode(request.code(), request.name()), null);
        return UserTypeMapper.userTypeDTO(userTypeRepository.save(UserTypeMapper.userTypeEntity(request)));
    }

    @Override
    @Transactional
    public UserTypeDTO update(UUID id, CreateUserTypeRequest request) {
        UserTypeEntity entity = findById(id);
        validateUnique(request.name(), UserTypeMapper.resolveCode(request.code(), request.name()), id);
        return UserTypeMapper.userTypeDTO(userTypeRepository.save(UserTypeMapper.userTypeEntity(entity, request)));
    }

    @Override
    @Transactional
    public String delete(UUID id) {
        userTypeRepository.delete(findById(id));
        return "User type deleted successfully";
    }

    private UserTypeEntity findById(UUID id) {
        return userTypeRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such user type found with this id")
        );
    }

    private void validateUnique(String name, String code, UUID id) {
        if (id == null) {
            if (userTypeRepository.existsByName(name)) {
                throw new DataAlreadyExistsException("User type already exists with this name");
            }
            if (userTypeRepository.existsByCode(code)) {
                throw new DataAlreadyExistsException("User type already exists with this code");
            }
            return;
        }

        if (userTypeRepository.existsByNameAndIdNot(name, id)) {
            throw new DataAlreadyExistsException("User type already exists with this name");
        }
        if (userTypeRepository.existsByCodeAndIdNot(code, id)) {
            throw new DataAlreadyExistsException("User type already exists with this code");
        }
    }
}
